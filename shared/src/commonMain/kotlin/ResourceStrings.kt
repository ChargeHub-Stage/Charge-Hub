import dev.icerock.moko.resources.StringResource

expect class ResourceStrings {

    fun get(id: StringResource, args: List<Any> = listOf()) : String

}