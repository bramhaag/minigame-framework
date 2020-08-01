package me.bramhaag.minigameframework.condition

import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.player.AbstractPlayer

/**
 * Victory Conditions are used to determine if the game is over.
 */
abstract class AbstractVictoryCondition {
    lateinit var phase : AbstractPhase
    var winner : AbstractPlayer? = null

    /**
     * Check if the game is completed, returns true if the winner is set
     */
    fun completed(): Boolean {
        return winner != null
    }
}