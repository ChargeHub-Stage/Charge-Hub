package screens.landing

sealed class LandingScreenUiEvent {

    data object ClickedLoginButtonEvent : LandingScreenUiEvent()

    data object ClickedRegisterButtonEvent : LandingScreenUiEvent()

}