package me.bramhaag.minigameframework.phase

import me.bramhaag.minigameframework.condition.AbstractVictoryCondition
import me.bramhaag.minigameframework.feature.AbstractFeature
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*

internal class AbstractPhaseTest {

    private lateinit var phase: AbstractPhase

    @BeforeEach
    fun init() {
        phase = object : AbstractPhase() {
            override fun init() {}
        }
    }

    @Test
    fun createFeature_Generic() {
        val feature = phase.createFeature<TestFeature>()
        assertNotNull(feature)
        assertEquals(TestFeature::class.java, feature::class.java)

        assertEquals(phase, feature.phase)
    }

    @Test
    fun createFeature_GenericWithInit() {
        val feature = phase.createFeature<TestFeature> { initValue = "My Value"}
        assertNotNull(feature)
        assertEquals(TestFeature::class.java, feature::class.java)

        assertEquals("My Value", feature.initValue)
        assertEquals(phase, feature.phase)
    }

    @Test
    fun createFeature_Instance() {
        val feature = phase.createFeature(TestFeature())
        assertNotNull(feature)
        assertEquals(TestFeature::class.java, feature::class.java)

        assertEquals(phase, feature.phase)
    }

    @Test
    fun createFeature_InstanceWithInit() {
        val feature = phase.createFeature(TestFeature()) { initValue = "My Value"}
        assertNotNull(feature)
        assertEquals(TestFeature::class.java, feature::class.java)

        assertEquals("My Value", feature.initValue)
        assertEquals(phase, feature.phase)
    }

    @Test
    fun addFeature() {
        assertTrue(phase.features.isEmpty())
        val feature = phase.createFeature<TestFeature>()
        phase.addFeature(feature)
        assertTrue(phase.features.contains(feature))
    }

    @Test
    fun addFeature_Duplicate() {
        assertTrue(phase.features.isEmpty())
        val feature1 = phase.createFeature<TestFeature>()
        val feature2 = phase.createFeature<TestFeature>()
        phase.addFeature(feature1)
        phase.addFeature(feature2)
        assertEquals(1, phase.features.size)
        assertTrue(phase.features.contains(feature1))
        assertFalse(phase.features.contains(feature2))
    }

    @Test
    fun getFeature() {
        val feature = phase.createFeature<TestFeature>()
        phase.addFeature(feature)

        assertEquals(feature, phase.getFeature(TestFeature::class.java))
    }

    @Test
    fun getFeature_NotFound() {
        assertNull(phase.getFeature(TestFeature::class.java))
    }

    @Test
    fun createCondition_Generic() {
        val condition = phase.createCondition<TestCondition>()
        assertNotNull(condition)
        assertEquals(TestCondition::class.java, condition::class.java)

        assertEquals(phase, condition.phase)
    }

    @Test
    fun createCondition_GenericWithInit() {
        val condition = phase.createCondition<TestCondition> { initValue = "My Value"}
        assertNotNull(condition)
        assertEquals(TestCondition::class.java, condition::class.java)

        assertEquals("My Value", condition.initValue)
        assertEquals(phase, condition.phase)
    }

    @Test
    fun createCondition_Instance() {
        val condition = phase.createCondition(TestCondition())
        assertNotNull(condition)
        assertEquals(TestCondition::class.java, condition::class.java)

        assertEquals(phase, condition.phase)
    }

    @Test
    fun createCondition_InstanceWithInit() {
        val condition = phase.createCondition(TestCondition()) { initValue = "My Value"}
        assertNotNull(condition)
        assertEquals(TestCondition::class.java, condition::class.java)

        assertEquals("My Value", condition.initValue)
        assertEquals(phase, condition.phase)
    }

    @Test
    fun addCondition() {
        assertTrue(phase.conditions.isEmpty())
        val condition = phase.createCondition<TestCondition>()
        phase.addCondition(condition)
        assertTrue(phase.conditions.contains(condition))
    }

    @Test
    fun addCondition_Duplicate() {
        assertTrue(phase.conditions.isEmpty())
        val condition1 = phase.createCondition<TestCondition>()
        val condition2 = phase.createCondition<TestCondition>()
        phase.addCondition(condition1)
        phase.addCondition(condition2)
        assertEquals(1, phase.conditions.size)
        assertTrue(phase.conditions.contains(condition1))
        assertFalse(phase.conditions.contains(condition2))
    }

    @Test
    fun getCondition() {
        val condition = phase.createCondition<TestCondition>()
        phase.addCondition(condition)

        assertEquals(condition, phase.getCondition(TestCondition::class.java))
    }

    @Test
    fun getCondition_NotFound() {
        assertNull(phase.getCondition(TestCondition::class.java))
    }

    internal class TestFeature : AbstractFeature() {
        var initValue: String? = null
    }
    
    internal class TestCondition : AbstractVictoryCondition() {
        var initValue: String? = null
    }
}