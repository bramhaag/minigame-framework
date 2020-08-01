package me.bramhaag.minigameframework.nukkit

import cn.nukkit.Server
import cn.nukkit.event.Listener
import cn.nukkit.plugin.Plugin
import me.bramhaag.minigameframework.game.AbstractGameHandler
import me.bramhaag.minigameframework.nukkit.listener.*
import me.bramhaag.minigameframework.spigot.listener.PlayerLeaveListener


class NukkitGameHandler(private val plugin: Plugin) : AbstractGameHandler(
        NukkitTickHandler(plugin),
        NukkitWorldHandler(plugin)
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
            Server.getInstance().pluginManager.registerEvents(it, plugin)
        }
    }
}