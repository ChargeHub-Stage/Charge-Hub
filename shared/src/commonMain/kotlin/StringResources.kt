import dev.icerock.moko.resources.StringResource

expect class StringResources {

    fun get(id: StringResource, args: List<Any> = listOf()) : String

}