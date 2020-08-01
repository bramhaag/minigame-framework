package me.bramhaag.minigameframework.world


abstract class AbstractWorldHandler {

    val worlds = HashMap<String, AbstractWorld>()

    abstract fun loadWorld(worldName: String) : AbstractWorld
    abstract fun unloadWorld(worldName: String)
    abstract fun isLoaded(worldName: String) : Boolean
    abstract fun deleteWorld(worldName: String) : Boolean
}