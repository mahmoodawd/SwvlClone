package com.example.swvlclone.auth

import android.content.Intent
import android.content.IntentSender

interface AuthUiClient {
    fun getSignedInUser(): UserData?
    suspend fun signOut()
    suspend fun signIn(): IntentSender?
    suspend fun signInWithIntent(intent: Intent): SignInResult
    suspend fun signInWithIdToken(googleIdToken: String): SignInResult
}