package screens.register

sealed class RegisterScreenUiAction {

    data class OnEmailChangedAction(val email: String) : RegisterScreenUiAction()

    data class OnFirstNameChangedAction(val firstName: String) : RegisterScreenUiAction()

    data class OnLastNameChangedAction(val lastName: String) : RegisterScreenUiAction()

    data class OnPasswordChangedAction(val password: String) : RegisterScreenUiAction()

    data class OnCarIdChangedAction(val vin: String) : RegisterScreenUiAction()

    data object OnNextClickedAction : RegisterScreenUiAction()

    data object OnPreviousClickedAction : RegisterScreenUiAction()

    data object OnFinaliseRegisterAction : RegisterScreenUiAction()
    data object OnPrivacyCheckedChangedAction : RegisterScreenUiAction()

}