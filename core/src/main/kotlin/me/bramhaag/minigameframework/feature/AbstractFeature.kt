package me.bramhaag.minigameframework.feature

import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.tick.ITickable

abstract class AbstractFeature : ITickable {

    lateinit var phase : AbstractPhase

    fun init() { }

    override fun enable() { }

    override fun disable() { }

    override fun tick() { }
}