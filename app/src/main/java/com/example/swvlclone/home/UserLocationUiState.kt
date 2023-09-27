package com.example.swvlclone.home

import com.example.swvlclone.domain.models.UserLocation

sealed interface UserLocationUiState {
    object Loading : UserLocationUiState
    object Empty : UserLocationUiState
    object Error : UserLocationUiState
    data class Success(
        val locations: List<UserLocation>
    ) : UserLocationUiState {
        fun isEmpty(): Boolean = locations.isEmpty()
    }
}