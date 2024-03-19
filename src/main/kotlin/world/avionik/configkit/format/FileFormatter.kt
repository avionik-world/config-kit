package world.avionik.configkit.format

import java.io.File

/**
 * @author Niklas Nieberler
 */

interface FileFormatter<V : Any> {

    /**
     * Saves your class in a file
     * @param file The file where your class is saved
     * @param value The class to save
     */
    fun save(file: File, value: V)

    /**
     * Gets the class from a file
     * @param file The file to get
     */
    fun get(file: File): V?

    /**
     * Gets the file name of the formatter
     * @return name of the file
     */
    fun getFileName(): String

    /**
     * Creates a file directory
     * @param file The file to get
     */
    fun createDirectory(file: File) {
        if (!file.path.contains("/"))
            return
        val path = file.path.substringBeforeLast("/")
        val directory = File(path)
        if (directory.exists())
            return
        directory.mkdirs()
    }

}