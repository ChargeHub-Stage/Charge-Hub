package screens.login

data class LoginScreenUiState(
    var email: String = "",
    var password: String = "",
    val passwordVisibility: Boolean = false
)