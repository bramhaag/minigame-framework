package me.bramhaag.minigameframework.player


enum class GameMode(val value: Int) {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    companion object {
        fun getByValue(value: Int): GameMode? {
            return values().firstOrNull { it.value == value }
        }
    }
}