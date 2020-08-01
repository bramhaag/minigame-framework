package me.bramhaag.minigameframework.nukkit.util

import cn.nukkit.block.BlockID
import cn.nukkit.item.Item
import cn.nukkit.item.ItemID
import me.bramhaag.minigameframework.nukkit.NukkitWorld
import me.bramhaag.minigameframework.util.Location

typealias NLocation = cn.nukkit.level.Location

private val MATERIALS = (BlockID::class.java.fields + ItemID::class.java.fields)
        .map { it.name to it.getInt(null) }
        .toMap()

fun materialToId(name: String) : Int? {
    return MATERIALS[name.toUpperCase().trim().replace("\\s+", "_")]
}

fun Location.Companion.fromNukkitLocation(loc: NLocation) : Location {
    //TODO world
    return Location(NukkitWorld(loc.level.name), loc.x, loc.y, loc.z, loc.yaw.toFloat(), loc.pitch.toFloat())
}