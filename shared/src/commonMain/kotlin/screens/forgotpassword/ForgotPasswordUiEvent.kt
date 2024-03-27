package screens.forgotpassword

sealed class ForgotPasswordUiEvent {
    data object ClickedBackEvent : ForgotPasswordUiEvent()
    data object ClickedSendEmailEvent : ForgotPasswordUiEvent()
}