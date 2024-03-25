package screens.login

data class LoginScreenUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false
)