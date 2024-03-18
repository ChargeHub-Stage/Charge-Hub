package screens.login

sealed class LoginScreenUiAction {
    data object OnClickedBackButtonAction : LoginScreenUiAction()
    data class OnEmailChangedAction(val email: String) : LoginScreenUiAction()
    data class OnPasswordChangedAction(val password: String) : LoginScreenUiAction()
    data object OnClickedPasswordVisibilityButtonAction : LoginScreenUiAction()
    data object OnClickedLoginButtonAction : LoginScreenUiAction()
    data object OnClickedForgotPasswordButtonAction : LoginScreenUiAction()
}