package me.bramhaag.minigameframework.spigot.util

import me.bramhaag.minigameframework.spigot.SpigotWorld
import me.bramhaag.minigameframework.util.Location
import java.lang.IllegalArgumentException

typealias BLocation = org.bukkit.Location

fun Location.Companion.fromBukkitLocation(loc: BLocation) : Location {
    val worldName = loc.world?.name ?: throw IllegalArgumentException("World is null")
    return Location(SpigotWorld(worldName), loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
}