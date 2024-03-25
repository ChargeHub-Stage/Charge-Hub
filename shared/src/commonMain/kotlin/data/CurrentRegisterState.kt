package data


/**
 * Enums representing the order of the registering pages.
 * The lowest is the starting point, the highest is the ending point.
 */
enum class CurrentRegisterState(private val order: Int) {
    EMAIL(1),
    PROFILE(2),
    CAR_CONNECT(3),
    NOTIFICATIONS(4),
    INFO(5);

    fun next(): CurrentRegisterState? {
        return entries.find { it.order == order + 1 }
    }

    fun previous(): CurrentRegisterState? {
        return entries.find { it.order == order - 1 }
    }
}