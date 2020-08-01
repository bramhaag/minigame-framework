package me.bramhaag.minigameframework.nukkit

import cn.nukkit.Server
import cn.nukkit.plugin.Plugin
import me.bramhaag.minigameframework.tick.AbstractTickHandler

class NukkitTickHandler(private val plugin: Plugin) : AbstractTickHandler() {

    override fun start() {
        Server.getInstance().scheduler.scheduleRepeatingTask(plugin, { tick() }, 1, false)
    }
}