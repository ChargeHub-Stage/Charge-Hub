package screens.register

data class RegisterScreenUiState(
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var password: String = "",
    var carId: String = "VSSZZZKL7PR048642",

    var currentRegisterState: CurrentRegisterState? = CurrentRegisterState.EMAIL,

    var isPrivacyChecked: Boolean = false,

    var isEmailValid: Boolean = false,
    var isFirstNameValid: Boolean = false,
    var isLastNameValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isCarIdValid: Boolean = false
)