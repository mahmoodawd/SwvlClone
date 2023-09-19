package com.example.swvlclone.auth

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit

class MobileAuthUiClient {
    val auth = Firebase.auth

    companion object {
        var storedVerificationId: String? = ""
            private set
    }

    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken


    fun startPhoneNumberVerification(
        phoneNumber: String,
        activity: Activity,
        codeTimeOutValue: Int,
        onCodeSent: () -> Unit = {},
        onVerificationFailed: (String) -> Unit = {}
    ) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Timber.i("onVerificationCompleted: $credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Timber.e("onVerificationFailed ${e.message}")
                onVerificationFailed(e.message.toString())
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Timber.d("onCodeSent:$verificationId")
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                onCodeSent()
            }

        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(codeTimeOutValue.toLong(), TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    suspend fun verifyPhoneNumberWithCode(
        activity: Activity,
        verificationId: String,
        code: String
    ): SignInResult {
        Timber.d("verifyPhoneNumberWithCode: $verificationId, $code")
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        val result = signInWithPhoneAuthCredential(credential, activity)
        Timber.d(result.toString())
        return result
    }

    private suspend fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        activity: Activity
    ): SignInResult {
        return try {
            val user = auth.signInWithCredential(credential)
                .await().user
            Timber.i("signInWithCredential:success")
            SignInResult(user = user?.toUserData())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(errorMessage = e.message)
        }
    }
    /* var result = SignInResult()
     auth.signInWithCredential(credential)
         .addOnCompleteListener(activity) { task ->
             if (task.isSuccessful) {
                 // Sign in success, update UI with the signed-in user's information
                 Timber.i("signInWithCredential:success")
                 val user = task.result?.user
                 result = SignInResult(user = user?.toUserData())
             } else {
                 // Sign in failed, display a message and update the UI
                 Timber.w("signInWithCredential:failure ${task.exception}")
                 if (task.exception is FirebaseAuthInvalidCredentialsException) {
                     // The verification code entered was invalid
                 }
                 // Update UI
             }
         }
     return result*/
}
