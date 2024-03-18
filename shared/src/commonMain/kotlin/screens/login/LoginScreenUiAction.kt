package screens.login

sealed class LoginScreenUiAction {
    data object ClickedBackButtonAction : LoginScreenUiAction()
    data class OnEmailChangedAction(val email: String) : LoginScreenUiAction()
    data class OnPasswordChangedAction(val password: String) : LoginScreenUiAction()
    data object ClickedLoginButtonAction : LoginScreenUiAction()
    data object ClickedForgotPasswordButtonAction : LoginScreenUiAction()
}