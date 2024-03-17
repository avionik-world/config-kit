package world.avionik.configkit

import world.avionik.configkit.format.FileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

abstract class MultipleConfigLoader<T : Nameable>(
    private val directory: File,
    private val fileFormatter: FileFormatter<T>
) {

    init {
        directory.mkdirs()
    }

    /**
     * Saves a config to file
     * @param value the object to save
     */
    fun save(value: T) {
        this.fileFormatter.save(getFileByObject(value), value)
    }

    /**
     * Loads all configs by a directory
     * @return list of all configs
     */
    fun loadAll(): Set<T> {
        return this.directory.listFiles()?.mapNotNull { this.fileFormatter.get(it) }?.toSet() ?: emptySet()
    }

    private fun getFileByObject(value: T): File {
        return File(this.directory, "${value.getName()}.${fileFormatter.getFileName()}")
    }

}