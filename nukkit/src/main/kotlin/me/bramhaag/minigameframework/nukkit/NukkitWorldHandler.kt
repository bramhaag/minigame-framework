package me.bramhaag.minigameframework.nukkit

import cn.nukkit.plugin.Plugin
import cn.nukkit.scheduler.NukkitRunnable
import me.bramhaag.minigameframework.world.AbstractWorld
import me.bramhaag.minigameframework.world.AbstractWorldHandler

class NukkitWorldHandler(private val plugin: Plugin) : AbstractWorldHandler() {

    override fun loadWorld(worldName: String): AbstractWorld {
        val world = NukkitWorld(worldName)
        world.load()

        worlds[worldName] = world
        return world
    }

    override fun unloadWorld(worldName: String) {
        val task = UnloadTimer(worlds[worldName] as NukkitWorld, false).runTaskTimer(plugin, 0, 10)
    }

    override fun isLoaded(worldName: String) : Boolean {
        return NukkitWorld(worldName).isLoaded()
    }

    override fun deleteWorld(worldName: String): Boolean {
        return NukkitWorld(worldName).deleteWorld()
    }

    class UnloadTimer(private val spigotWorld: NukkitWorld, private val save: Boolean) : NukkitRunnable() {
        override fun run() {
            if(spigotWorld.unload(save)) {
                cancel()
            }
        }
    }

}