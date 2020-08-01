package me.bramhaag.minigameframework.spigot

import me.bramhaag.minigameframework.game.AbstractGameHandler
import me.bramhaag.minigameframework.spigot.listener.*
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


class SpigotGameHandler(private val plugin: JavaPlugin) : AbstractGameHandler(
        SpigotTickHandler(plugin),
        SpigotWorldHandler(plugin)
) {

    init {
        registerEvents(
                PlayerBlockBreakListener(this),
                PlayerBlockPlaceListener(this),
                PlayerDamageListener(this),
                PlayerDeathListener(this),
                PlayerDropListener(this),
                PlayerJoinListener(),
                PlayerLeaveListener(this),
                PlayerMoveListener(this)
        )

        tickHandler.start()
    }

    private fun registerEvents(vararg events: Listener) {
        events.forEach {
            Bukkit.getServer().pluginManager.registerEvents(it, plugin)
        }
    }
}