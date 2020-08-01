package me.bramhaag.minigameframework.game


enum class JoinStatus {
    /**
     * Player cannot join for an unspecified reason
     */
    CANNOT_JOIN,
    /**
     * Player cannot join because he is currently in a game
     */
    CANNOT_JOIN_IN_GAME,
    /**
     * Player joined as a spectator
     */
    SPECTATOR,
    /**
     * Player joined successfully
     */
    PLAYER
}