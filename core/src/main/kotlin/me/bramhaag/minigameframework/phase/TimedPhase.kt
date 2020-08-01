package me.bramhaag.minigameframework.phase

abstract class TimedPhase(allowJoin: Boolean = false,
                          allowSpectate: Boolean = false,
                          nextPhase: AbstractPhase? = null,
                          var duration: Long = 0
) : AbstractPhase(allowJoin, allowSpectate, nextPhase) {
    var currentTick = 0L
        private set

    override fun tick() {
        if(currentTick >= duration) {
            game.endPhase()
            return
        }

        currentTick++
    }
}