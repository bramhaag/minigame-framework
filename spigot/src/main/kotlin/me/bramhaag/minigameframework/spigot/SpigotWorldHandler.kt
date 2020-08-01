package me.bramhaag.minigameframework.spigot

import me.bramhaag.minigameframework.world.AbstractWorld
import me.bramhaag.minigameframework.world.AbstractWorldHandler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class SpigotWorldHandler(private val plugin: JavaPlugin) : AbstractWorldHandler() {

    override fun loadWorld(worldName: String): AbstractWorld {
        val world = SpigotWorld(worldName)
        world.load()

        worlds[worldName] = world
        return world
    }

    override fun unloadWorld(worldName: String) {
        val task = UnloadTimer(worlds[worldName] as SpigotWorld, false).runTaskTimer(plugin, 0L, 10L)
    }

    override fun isLoaded(worldName: String) : Boolean {
        return SpigotWorld(worldName).isLoaded()
    }

    override fun deleteWorld(worldName: String): Boolean {
        return SpigotWorld(worldName).deleteWorld()
    }

    class UnloadTimer(private val spigotWorld: SpigotWorld, private val save: Boolean) : BukkitRunnable() {
        override fun run() {
            if(spigotWorld.unload(save)) {
                cancel()
            }
        }
    }

}