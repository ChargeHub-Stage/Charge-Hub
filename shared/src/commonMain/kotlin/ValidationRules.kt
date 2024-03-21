
object ValidationRules {

    fun isValidFirstName(name: String) = ensureNotEmpty(name) && ensureMaxLength(name, 12)

    fun isValidLastName(name: String) = ensureNotEmpty(name) && ensureMinLength(name, 2)

    fun isValidPassword(password: String) = ensureNotEmpty(password) && ensureMinLength(password, 3)

    fun isEmailValid(email: String) = email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())


    // Regex comes from https://regex101.com/r/49qCxI/1
    fun isValidVIN(vin: String) = Regex("""\b[A-HJ-NPR-Z0-9]{17}\b""").matches(vin)

    private fun ensureNotEmpty(input: String) = input.isNotBlank() && input.isNotEmpty()

    private fun ensureMinLength(input: String, minLength: Int) = input.length >= minLength

    private fun ensureMaxLength(input: String, maxLength: Int) = input.length <= maxLength

    private fun ensureMinCapitals(input: String, minCapitals: Int) = input.count { it.isUpperCase() } <= minCapitals

    private fun ensureMinNumbers(input: String, minNumbers: Int) = input.count { it.isDigit() } >= minNumbers

}