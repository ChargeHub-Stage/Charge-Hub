package screens.login

import com.rickclephas.kmm.viewmodel.coroutineScope
import db.repository.FirebaseRepository
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import screens.AbstractViewModel

class LoginScreenViewModel(private val firebaseRepo: FirebaseRepository) :
    AbstractViewModel<LoginScreenUiAction, LoginScreenUiEvent, LoginScreenUiState>() {

    override var state: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(viewModelScope, LoginScreenUiState())

    override fun onAction(action: LoginScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is LoginScreenUiAction.OnClickedBackButtonAction -> {sendEvent(LoginScreenUiEvent.ClickedBackButtonEvent) }
            is LoginScreenUiAction.OnEmailChangedAction -> state.value = state.value.copy(email = action.email)
            is LoginScreenUiAction.OnPasswordChangedAction -> state.value = state.value.copy(password = action.password)
            is LoginScreenUiAction.OnClickedPasswordVisibilityButtonAction -> state.value = state.value.copy(passwordVisibility = !state.value.passwordVisibility)
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