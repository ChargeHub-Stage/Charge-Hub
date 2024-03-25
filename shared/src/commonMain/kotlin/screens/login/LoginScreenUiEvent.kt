package screens.login

sealed class LoginScreenUiEvent {
    data object ClickedBackButtonEvent : LoginScreenUiEvent()
    data object ClickedLoginButtonEvent : LoginScreenUiEvent()
    data object ClickedForgotPasswordButtonEvent : LoginScreenUiEvent()
}