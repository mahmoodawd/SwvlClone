package com.example.swvlclone.auth

data class SignInResult(
    val user: UserData? = null,
    val errorMessage: String? = null
)

data class UserData(
    val userId: String,
    val userName: String?,
    val phoneNumber:String?,
    val email: String?,
    val profilePhotoUrl: String
)

