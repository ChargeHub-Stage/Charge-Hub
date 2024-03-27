package screens.forgotpassword

sealed class ForgotPasswordUiAction {
    data object OnClickedBackAction : ForgotPasswordUiAction()
    data object OnClickedSendEmailAction : ForgotPasswordUiAction()
    data class OnEmailChangedAction(val email: String) : ForgotPasswordUiAction()
}