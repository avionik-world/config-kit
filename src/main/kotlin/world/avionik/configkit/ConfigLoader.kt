package world.avionik.configkit

import world.avionik.configkit.format.FileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

abstract class ConfigLoader<T : Any>(
    private val configFile: File,
    private val fileFormatter: FileFormatter<T>,
    private val lazyDefaultObject: () -> T
) {

    init {
        fileFormatter.createDirectory(this.configFile)
    }

    /**
     * Loads the config class
     * @return config class
     */
    fun load(): T {
        val objectFromFile = this.fileFormatter.get(this.configFile)
        if (objectFromFile == null) {
            val defaultObject = this.lazyDefaultObject()
            if (!doesConfigFileExist())
                saveConfig(defaultObject)
            return defaultObject
        }
        return objectFromFile
    }

    /**
     * Saves a config to file
     * @param value the object to save
     */
    fun saveConfig(value: T) {
        this.fileFormatter.save(this.configFile, value)
    }

    fun doesConfigFileExist(): Boolean = this.configFile.exists()

}