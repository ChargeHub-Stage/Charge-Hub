package screens.login

import com.rickclephas.kmm.viewmodel.coroutineScope
import db.repository.FirebaseRepository
import kotlinx.coroutines.launch
import screens.AbstractViewModel

class LoginScreenViewModel(private val firebaseRepo: FirebaseRepository) :
    AbstractViewModel<LoginScreenUiAction, LoginScreenUiEvent, LoginScreenUiState>(
        LoginScreenUiState()
    ) {
    fun setEmail(email: String) {
        _state.value = state.value.copy(email = email)
    }
    fun setPassword(password: String) {
        _state.value = state.value.copy(password = password)
    }
    fun setPasswordVisibility(visibility: Boolean) {
        _state.value = state.value.copy(passwordVisibility = visibility)
    }
    fun getState(): LoginScreenUiState {
        return state.value
    }

    override fun onAction(action: LoginScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is LoginScreenUiAction.OnClickedBackButtonAction -> {
                sendEvent(LoginScreenUiEvent.ClickedBackButtonEvent)
            }
            is LoginScreenUiAction.OnEmailChangedAction -> _state.value =
                state.value.copy(email = action.email)
            is LoginScreenUiAction.OnPasswordChangedAction -> _state.value =
                state.value.copy(password = action.password)
            is LoginScreenUiAction.OnClickedPasswordVisibilityButtonAction -> _state.value =
                state.value.copy(passwordVisibility = ! state.value.passwordVisibility)
            is LoginScreenUiAction.OnClickedLoginButtonAction -> firebaseRepo.login(
                state.value.email,
                state.value.password
            ) {
                trySend(LoginScreenUiEvent.ClickedLoginButtonEvent)
            }
            is LoginScreenUiAction.OnClickedForgotPasswordButtonAction -> sendEvent(
                LoginScreenUiEvent.ClickedForgotPasswordButtonEvent
            )
        }
    }
}