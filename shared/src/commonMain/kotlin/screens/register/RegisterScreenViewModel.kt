package screens.register

import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging
import screens.AbstractViewModel

class RegisterScreenViewModel :
    AbstractViewModel<RegisterScreenUiAction, RegisterScreenUiEvent, RegisterScreenUiState>() {

    override var state: MutableStateFlow<RegisterScreenUiState> =
        MutableStateFlow(viewModelScope, RegisterScreenUiState())


    override fun onAction(action: RegisterScreenUiAction) = viewModelScope.coroutineScope.launch {
        val logging = logging()
        when (action) {
            is RegisterScreenUiAction.OnNextClickedAction -> handleNext()
            is RegisterScreenUiAction.OnPreviousClickedAction -> handlePrevious()
            is RegisterScreenUiAction.OnEmailChangedAction -> state.value = state.value.copy(email = action.email)

            is RegisterScreenUiAction.OnFirstNameChangedAction -> state.update { it.copy(firstName = action.firstName) }
            is RegisterScreenUiAction.OnLastNameChangedAction -> state.update { it.copy(lastName = action.lastName) }
            is RegisterScreenUiAction.OnPasswordChangedAction -> state.update { it.copy(password = action.password) }
            is RegisterScreenUiAction.OnFinaliseRegisterAction -> { /*TODO */
            }
        }
    }

    private suspend fun handleNext() {
        state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.next())
        }
        sendEvent(RegisterScreenUiEvent.OnNextClickedEvent)
    }

    private suspend fun handlePrevious() {
        state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.previous())
        }
        sendEvent(RegisterScreenUiEvent.OnPreviousClickedEvent)
    }

}

/**
 * Enums representing the order of the registering pages.
 * The lowest is the starting point, the highest is the ending point.
 */
enum class CurrentRegisterState(private val order: Int) {
    EMAIL(1),
    PROFILE(2),
    CAR_CONNECT(3),
    FACE_ID(4),
    NOTIFICATIONS(5),
    INFO(6);

    fun next(): CurrentRegisterState? {
        return entries.find { it.order == order + 1 }
    }

    fun previous(): CurrentRegisterState? {
        return entries.find { it.order == order - 1 }
    }
}
