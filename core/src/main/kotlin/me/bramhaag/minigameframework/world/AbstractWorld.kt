package me.bramhaag.minigameframework.world

import me.bramhaag.minigameframework.util.Location

abstract class AbstractWorld(val worldName: String) {

    val originalPrefix = "original-"

    abstract fun load()
    abstract fun unload(save: Boolean) : Boolean
    abstract fun isLoaded() : Boolean
    abstract fun deleteWorld() : Boolean
    abstract fun getSpawn() : Location

    override fun toString(): String {
        return "AbstractWorld(worldName='$worldName')"
    }
}
