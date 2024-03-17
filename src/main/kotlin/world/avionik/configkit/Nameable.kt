package world.avionik.configkit

/**
 * @author Niklas Nieberler
 */

// Represents an object that needs its own name.
interface Nameable {

    /**
     * Gets the name of this object
     * @return name of object
     */
    fun getName(): String

}