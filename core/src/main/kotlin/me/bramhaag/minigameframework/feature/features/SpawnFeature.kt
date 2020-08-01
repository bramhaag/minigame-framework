package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.util.Location
import me.bramhaag.minigameframework.world.AbstractWorld
import sun.plugin.dom.exception.InvalidStateException


class SpawnFeature : AbstractFeature() {


    private var spawnPointsTemp = ArrayList<Location>()
    private var spawnPoints = ArrayList<Location>()
    private var counter = 0

    override fun enable() {
        val world = phase.getFeature(WorldFeature::class.java)?.world ?: throw IllegalStateException("World cannot be null")

        spawnPointsTemp.map { Location(world, it.x, it.y, it.z, it.yaw, it.pitch) }.forEach { spawnPoints.add(it) }
        phase.game.players.forEach { spawn(it) }
    }

    @GameEvent
    fun onJoin(e: GameJoinEvent) {
        spawn(e.player)
    }

    fun addSpawn(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        spawnPointsTemp.add(Location(null, x, y, z, yaw, pitch))
    }

    private fun spawn(player: AbstractPlayer) {
        if(spawnPoints.isEmpty()) throw InvalidStateException("Spawn points cannot be empty!")

        if(counter > spawnPoints.size - 1) counter = 0

        val location = spawnPoints[counter]
        location.world?.load()
        player.setLocation(spawnPoints[counter])
        counter += 1
    }
}