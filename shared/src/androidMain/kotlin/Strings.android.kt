import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

actual class ResourceStrings(private val context: Context) {
    actual fun get(
        id: StringResource,
        args: List<Any>
    ) : String {
        return if (args.isEmpty()) {
            id.format(*args.toTypedArray()).toString(context = context)
        } else {
            StringDesc.Resource(id).toString(context = context)
        }
    }

}