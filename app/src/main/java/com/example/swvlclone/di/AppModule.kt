package com.example.swvlclone.di

import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.auth.GoogleAuthUiClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsGoogleAuthUiClient(authUiClient: GoogleAuthUiClient): AuthUiClient


}