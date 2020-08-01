package me.bramhaag.minigameframework.event

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.handler.IHandler
import me.bramhaag.minigameframework.player.AbstractPlayer
import java.lang.IllegalArgumentException

class EventHandler : IHandler {

    val events = HashMap<Class<out MinigameEvent>, ArrayList<EventData>>()

    @Suppress("UNCHECKED_CAST")
    fun registerEvents(feature: AbstractFeature) {
        val events = feature::class.java.methods
                .filter { it.isAnnotationPresent(GameEvent::class.java) }

        for (event in events) {
            if (event.parameters.size != 1) {
                throw IllegalArgumentException("Invalid argument length in " +
                        "${feature::class.qualifiedName}#${event.name}. Expected 1, got ${event.parameters.size}")
            }

            val parameterType = event.parameters.first().type
            if (!MinigameEvent::class.java.isAssignableFrom(parameterType)) {
                throw IllegalArgumentException("Parameter ${event.parameters[0].name} in " +
                        "${feature::class.qualifiedName}#${event.name} should be of type 'MinigameEvent'.")
            }

            // Cast is ok as it is checked in the line above
            val eventType = parameterType as Class<MinigameEvent>

            val eventList = this.events.getOrPut(eventType, { ArrayList() })
            eventList.add(EventData(feature, event, feature.phase.game))
        }
    }

    fun unregisterEvents() {
        events.clear()
    }

    fun callEvent(player: AbstractPlayer, event: MinigameEvent) : Boolean {
        events[event::class.java]
                ?.filter { it.game.id == player.game?.id }
                ?.forEach { it.method.invoke(it.listener, event) }

        return event.cancelled
    }
}