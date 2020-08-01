package me.bramhaag.minigameframework.spigot

import me.bramhaag.minigameframework.tick.AbstractTickHandler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class SpigotTickHandler(private val plugin: JavaPlugin) : AbstractTickHandler() {

    override fun start() {
        Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(plugin, { tick() }, 1L, 1L)
    }
}