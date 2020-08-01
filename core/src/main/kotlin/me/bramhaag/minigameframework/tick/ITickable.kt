package me.bramhaag.minigameframework.tick


interface ITickable {

    /**
     * Called then the [ITickable] is enabled
     */
    fun enable()

    /**
     * Called then the [ITickable] is disabled
     */
    fun disable()

    /**
     * Called every tick when [ITickable] is enabled
     */
    fun tick()
}