package com.example.swvlclone.di

import com.example.swvlclone.auth.AuthUiClient
import com.example.swvlclone.auth.GoogleAuthUiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsGoogleAuthUiClient(authUiClient: GoogleAuthUiClient): AuthUiClient

    companion object {
        @Singleton
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Singleton
        @Provides
        fun providesFireStore(): FirebaseFirestore = Firebase.firestore
    }
}