package screens.register

import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import screens.AbstractViewModel

class RegisterScreenViewModel :
    AbstractViewModel<RegisterScreenUiAction, RegisterScreenUiEvent, RegisterScreenUiState>(
        RegisterScreenUiState()
    ) {


    override fun onAction(action: RegisterScreenUiAction) = viewModelScope.coroutineScope.launch {
       // val logging = logging()
        when (action) {
            is RegisterScreenUiAction.OnNextClickedAction -> handleNext()
            is RegisterScreenUiAction.OnPreviousClickedAction -> handlePrevious()
            is RegisterScreenUiAction.OnEmailChangedAction -> _state.value = state.value.copy(email = action.email)

            is RegisterScreenUiAction.OnFirstNameChangedAction -> _state.update { it.copy(firstName = action.firstName) }
            is RegisterScreenUiAction.OnLastNameChangedAction -> _state.update { it.copy(lastName = action.lastName) }
            is RegisterScreenUiAction.OnPasswordChangedAction -> _state.update { it.copy(password = action.password) }
            is RegisterScreenUiAction.OnFinaliseRegisterAction -> { /*TODO */
            }

            RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> _state.update { it.copy(isPrivacyChecked = !_state.value.isPrivacyChecked) }
        }
    }

    private suspend fun handleNext() {
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
