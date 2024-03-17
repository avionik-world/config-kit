package world.avionik.configkit.format

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.KSerializer
import java.io.File

/**
 * @author Niklas Nieberler
 */

class YamlFileFormatter<T : Any>(
    private val serializer: KSerializer<T>
) : FileFormatter<T> {

    override fun save(file: File, value: T) {
        val yamlText = Yaml.default.encodeToString(this.serializer, value)
        file.writeText(yamlText)
        file.createNewFile()
    }

    override fun get(file: File): T? {
        if (!file.exists())
            return null
        return Yaml.default.decodeFromString(this.serializer, file.readText())
    }

    override fun getFileName(): String = "yaml"

}