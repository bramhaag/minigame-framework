package me.bramhaag.minigameframework.phase

import me.bramhaag.minigameframework.condition.AbstractVictoryCondition
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.feature.features.SpawnFeature
import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.tick.ITickable


abstract class AbstractPhase(var allowJoin: Boolean = false,
                             var allowSpectate: Boolean = false,
                             var nextPhase: AbstractPhase? = null
) : ITickable {
    val features = ArrayList<AbstractFeature>()
    val conditions = ArrayList<AbstractVictoryCondition>()

    lateinit var game: AbstractGame

    /**
     * Called when the phase is started
     */
    abstract fun init()

    override fun enable() { }
    override fun disable() { }
    override fun tick() { }

    /**
     * Create [AbstractFeature] of type [T]
     *
     * @return Initialized [AbstractFeature] of type [T]
     */
    inline fun <reified T: AbstractFeature> createFeature() : T {
        return createFeature(T::class.java.newInstance())
    }

    /**
     * Create [AbstractFeature] of type [T]
     *
     * @param init Values for the [AbstractFeature] of type [T]
     *
     * @return Initialized [AbstractFeature] of type [T]
     */
    inline fun <reified T: AbstractFeature> createFeature(noinline init: T.() -> Unit) : T {
        return createFeature(T::class.java.newInstance(), init)
    }

    /**
     * Create [AbstractFeature] of type [T]
     *
     * @param feature Instance of [AbstractFeature] of type [T]
     * @param init Values for the [AbstractFeature] of type [T]
     *
     * @return Initialized [AbstractFeature] of type [T]
     */
    fun <T: AbstractFeature> createFeature(feature: T, init: (T.() -> Unit)? = null) : T {
        if(init != null) {
            init(feature)
        }

        feature.phase = this
        addFeature(feature)

        return feature
    }


    /**
     * Register feature
     * @param feature Feature to register
     */
    fun addFeature(feature: AbstractFeature) {
        if(getFeature(feature::class.java) == null)
            features.add(feature)
    }

    /**
     * Get [AbstractFeature] of type [T] if registered
     * @param klass type of feature
     */
    fun <T: AbstractFeature> getFeature(klass: Class<T>) : T? {
        return features.firstOrNull { klass.isInstance(it) } as? T
    }

    /**
     * Create [AbstractVictoryCondition] of type [T]
     *
     * @return Initialized [AbstractVictoryCondition] of type [T]
     */
    inline fun <reified T: AbstractVictoryCondition> createCondition() : T {
        return createCondition(T::class.java.newInstance())
    }

    /**
     * Create [AbstractVictoryCondition] of type [T]
     *
     * @param init Values for the [AbstractVictoryCondition] of type [T]
     *
     * @return Initialized [AbstractVictoryCondition] of type [T]
     */
    inline fun <reified T: AbstractVictoryCondition> createCondition(noinline init: T.() -> Unit) : T {
        return createCondition(T::class.java.newInstance(), init)
    }

    /**
     * Create [AbstractVictoryCondition] of type [T]
     *
     * @param condition Instance of [AbstractVictoryCondition] of type [T]
     * @param init Values for the [AbstractVictoryCondition] of type [T]
     *
     * @return Initialized [AbstractVictoryCondition] of type [T]
     */
    fun <T: AbstractVictoryCondition> createCondition(condition: T, init: (T.() -> Unit)? = null) : T {
        if (init != null) {
            init(condition)
        }

        condition.phase = this
        addCondition(condition)

        return condition
    }

    fun addCondition(condition: AbstractVictoryCondition) {
        if(getCondition(condition::class.java) == null)
            conditions.add(condition)
    }

    /**
     * Get [AbstractVictoryCondition] of type [T] if registered
     * @param klass type of feature
     */
    fun <T: AbstractVictoryCondition> getCondition(klass: Class<T>) : T? {
        return conditions.firstOrNull { klass.isInstance(it) } as? T
    }
}