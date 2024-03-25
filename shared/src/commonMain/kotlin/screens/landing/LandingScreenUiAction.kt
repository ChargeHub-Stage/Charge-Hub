package screens.landing

sealed class LandingScreenUiAction {

    data object ClickedLoginButtonAction : LandingScreenUiAction()

    data object ClickedRegisterButtonAction : LandingScreenUiAction()

}