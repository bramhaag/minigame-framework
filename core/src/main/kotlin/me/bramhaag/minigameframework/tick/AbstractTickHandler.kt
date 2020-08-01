package me.bramhaag.minigameframework.tick

import me.bramhaag.minigameframework.handler.IHandler


abstract class AbstractTickHandler : IHandler {

    val removeQueue = ArrayList<ITickable>()
    val tickables = ArrayList<ITickable>()

    abstract fun start()

    fun tick() {
        removeQueue.forEach { it.disable() }
        tickables.removeAll(removeQueue)
        removeQueue.clear()

        ArrayList(tickables).forEach { it.tick() }
    }

    fun registerTickable(tickable: ITickable) {
        tickables.add(tickable)
        tickable.enable()
    }

    fun endTickable(tickable: ITickable) {
        if (tickables.contains(tickable)) {
            removeQueue.add(tickable)
        }
    }
}