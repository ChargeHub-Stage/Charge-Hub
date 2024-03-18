package screens.login

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import screens.IViewModel

class LoginScreenViewModel : KMMViewModel(),
    IViewModel<LoginScreenUiAction, LoginScreenUiEvent, LoginScreenUiState> {
    private val eventChannel = Channel<LoginScreenUiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    override var state: LoginScreenUiState = LoginScreenUiState()

    override fun onAction(action: LoginScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is LoginScreenUiAction.OnClickedBackButtonAction -> eventChannel.send(LoginScreenUiEvent.ClickedBackButtonEvent)
            is LoginScreenUiAction.OnEmailChangedAction -> state = state.copy(email = action.email)
            is LoginScreenUiAction.OnPasswordChangedAction -> state = state.copy(password = action.password)
            is LoginScreenUiAction.OnClickedPasswordVisibilityButtonAction -> state = state.copy(passwordVisibility = !state.passwordVisibility)
            is LoginScreenUiAction.OnClickedLoginButtonAction -> eventChannel.send(LoginScreenUiEvent.ClickedLoginButtonEvent)
            is LoginScreenUiAction.OnClickedForgotPasswordButtonAction -> eventChannel.send(LoginScreenUiEvent.ClickedForgotPasswordButtonEvent)
        }
    }
}