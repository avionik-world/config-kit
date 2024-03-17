package world.avionik.configkit.format

import com.google.gson.Gson
import eu.thesimplecloud.jsonlib.JsonLib
import java.io.File

/**
 * @author Niklas Nieberler
 */

class JsonFileFormatter<V : Any>(
    private val configClass: Class<V>
) : FileFormatter<V> {

    override fun save(file: File, value: V) {
        JsonLib.fromObject(value).saveAsFile(file)
    }

    override fun get(file: File): V? {
        return JsonLib.fromJsonFile(file)?.getObjectOrNull(this.configClass)
    }

    override fun getFileName(): String = "json"

    override fun createDirectory(file: File) { }

    companion object {
        fun <T : Any> of(configClass: Class<T>): JsonFileFormatter<T> {
            return JsonFileFormatter(configClass)
        }
    }

}

inline fun <reified T : Any> JsonFileFormatter.Companion.of(): JsonFileFormatter<T> {
    return this.of(T::class.java)
}