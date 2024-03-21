
object ValidationRules {

    fun isValidFirstName(name: String) = ensureNotEmpty(name) && ensureMaxLength(name, 12)

    fun isValidLastName(name: String) = ensureNotEmpty(name) && ensureMinLength(name, 3)

    // This is based of the min requirements of firebase
    fun isValidPassword(password: String) = ensureNotEmpty(password) && ensureMinLength(password, 6)

    // Firebase email regex https://stackoverflow.com/questions/66025668/test-email-with-regular-expression-via-firebase-firestore-security-rule
    fun isEmailValid(email: String) = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex())


    // Regex comes from https://regex101.com/r/49qCxI/1
    fun isValidVIN(vin: String) = Regex("""\b[A-HJ-NPR-Z0-9]{17}\b""").matches(vin)

    private fun ensureNotEmpty(input: String) = input.isNotBlank() && input.isNotEmpty()

    private fun ensureMinLength(input: String, minLength: Int) = input.length >= minLength

    private fun ensureMaxLength(input: String, maxLength: Int) = input.length <= maxLength

    private fun ensureMinCapitals(input: String, minCapitals: Int) = input.count { it.isUpperCase() } <= minCapitals

    private fun ensureMinNumbers(input: String, minNumbers: Int) = input.count { it.isDigit() } >= minNumbers

}