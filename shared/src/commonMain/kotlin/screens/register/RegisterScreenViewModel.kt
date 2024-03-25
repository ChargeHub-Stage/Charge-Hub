package screens.register

import com.rickclephas.kmm.viewmodel.coroutineScope
import data.CurrentRegisterState
import db.repository.car.RemoteCarRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import screens.AbstractViewModel

class RegisterScreenViewModel :
    AbstractViewModel<RegisterScreenUiAction, RegisterScreenUiEvent, RegisterScreenUiState>(
        RegisterScreenUiState()
    ) {

    private val carRepository: RemoteCarRepository by inject()

    override fun onAction(action: RegisterScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is RegisterScreenUiAction.OnNextClickedAction -> handleNext()
            is RegisterScreenUiAction.OnPreviousClickedAction -> handlePrevious()

            is RegisterScreenUiAction.OnFinaliseRegisterAction -> { /*TODO */
            }

            is RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> _state.update {
                it.copy(
                    isPrivacyChecked = !state.value.isPrivacyChecked
                )
            }

            is RegisterScreenUiAction.OnCarIdChangedAction -> _state.update { it.copy(vin = action.id) }
            is RegisterScreenUiAction.OnEmailChangedAction -> _state.value =
                state.value.copy(email = action.email)

            is RegisterScreenUiAction.OnFirstNameChangedAction -> _state.update { it.copy(firstName = action.firstName) }
            is RegisterScreenUiAction.OnLastNameChangedAction -> _state.update { it.copy(lastName = action.lastName) }
            is RegisterScreenUiAction.OnPasswordChangedAction -> _state.update { it.copy(password = action.password) }

            RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> _state.update {
                it.copy(
                    isPrivacyChecked = !_state.value.isPrivacyChecked
                )
            }
        }
    }

    private suspend fun handleNext() {
        if (state.value.currentRegisterState == CurrentRegisterState.CAR_CONNECT) {
            carRepository.fetchCarConnectData(state.value.vin)
        }
        _state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.next())
        }
        sendEvent(RegisterScreenUiEvent.OnNextClickedEvent)
    }


    private suspend fun handlePrevious() {
        _state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.previous())
        }
        sendEvent(RegisterScreenUiEvent.OnPreviousClickedEvent)
    }
}