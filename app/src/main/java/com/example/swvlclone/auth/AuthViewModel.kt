package com.example.swvlclone.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    var uiState = mutableStateOf(SignInState())
        private set

    fun onSignInResult(result: SignInResult) {

        uiState.value = uiState.value.copy(
            isSuccessful = result.user != null,
            isError = result.errorMessage
        )
        Timber.i(result.toString())
    }

    fun resetState() {
        uiState.value = SignInState()
    }

}