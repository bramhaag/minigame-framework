package me.bramhaag.minigameframework.util

import me.bramhaag.minigameframework.world.AbstractWorld


class Location(val world: AbstractWorld?, var x: Double, var y: Double, var z: Double, var yaw: Float = 0.0f, var pitch: Float = 0.0f) {
    override fun toString(): String {
        return "Location(world=$world, x=$x, y=$y, z=$z, yaw=$yaw, pitch=$pitch)"
    }

    companion object
}