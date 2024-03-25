package screens.register

import data.CurrentRegisterState

data class RegisterScreenUiState(
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var password: String = "",

    var currentRegisterState: CurrentRegisterState? = CurrentRegisterState.EMAIL,

    var isEmailValid: Boolean = false,
    var isFirstNameValid: Boolean = false,
    var isLastNameValid: Boolean = false,
    var isPasswordValid: Boolean = false
)