package com.example.swvlclone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swvlclone.comon.result.asResult
import com.example.swvlclone.domain.models.UserLocation
import com.example.swvlclone.domain.usecase.AddNewLocationUseCase
import com.example.swvlclone.domain.usecase.GetUserLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.example.swvlclone.comon.result.Result as LocationResult

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLocationsUseCase: GetUserLocationsUseCase,
    private val addNewLocationUseCase: AddNewLocationUseCase,
) : ViewModel() {

    var userLocationsUiState: MutableStateFlow<UserLocationUiState> =
        MutableStateFlow(UserLocationUiState.Empty)
        private set

    fun getUserLocations() {
        viewModelScope.launch {
            userLocationsUiState.value = UserLocationUiState.Loading

            getUserLocationsUseCase().asResult()
                .map { result ->
                    when (result) {
                        is LocationResult.Success -> {
                            Timber.d("UserLocations: ${result.data.locations}")
                            userLocationsUiState.value = UserLocationUiState.Success(
                                locations = result.data.locations
                            )
                        }

                        is LocationResult.Error -> {
                            Timber.e("Error getting UserLocations: ${result.exception}")
                            userLocationsUiState.value = UserLocationUiState.Error
                        }

                        is LocationResult.Loading -> userLocationsUiState.value =
                            UserLocationUiState.Loading
                    }
                }.stateIn(
                    viewModelScope,
                )

        }
    }


    fun addNewLocation(userLocation: UserLocation) {
        viewModelScope.launch { addNewLocationUseCase(userLocation) }
    }
}


