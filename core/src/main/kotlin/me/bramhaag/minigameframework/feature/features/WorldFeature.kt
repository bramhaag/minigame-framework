package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.world.AbstractWorld


class WorldFeature : AbstractFeature() {

    var worldName = ""
    var firstLoad = false
    var shouldUnload = false

    lateinit var world: AbstractWorld

    override fun enable() {
        if(firstLoad) {
            phase.game.gameHandler.worldHandler.deleteWorld(worldName)
        }


        world = phase.game.gameHandler.worldHandler.loadWorld(worldName)
    }

    override fun disable() {
        if(!shouldUnload) return

        phase.game.gameHandler.worldHandler.unloadWorld(worldName)
    }
}