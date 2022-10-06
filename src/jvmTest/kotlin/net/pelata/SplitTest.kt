package net.pelata

import kotlin.test.*
import net.pelata.features.pace.model.Split
import net.pelata.units.Distance

class SplitTest {

    @Test
    fun testAveragePace() {
        val split = Split(10.0, 60.0)
        val avg = split.averagePace

        assertEquals(6.0, avg, 0.0001)
    }

    @Test
    fun testAverageSpeedKmh() {
        val split = Split(10.0, 60.0, Distance.KILOMETERS)
        val spd = split.averageSpeed
        val spdKmh = split.averageSpeedKmh

        assertEquals(10.0, spd, 0.0001)
        assertEquals(10.0, spdKmh, 0.0001)
    }

    @Test
    fun testAverageSpeedMph() {
        val split = Split(10.0, 60.0, Distance.MILES)
        val spd = split.averageSpeed
        val spdKmh = split.averageSpeedKmh

        assertEquals(10.0, spd, 0.0001)
        assertEquals(16.09334, spdKmh, 0.0001)
    }

    @Test
    fun testDistances1() {
        val split = Split(10.33, 60.0)

        val distances = split.distances().splitDistances
        assertEquals(11, distances.size)
        assertEquals(1.0, distances[0], 0.0001)
        assertEquals(10.0, distances[9], 0.0001)
        assertEquals(10.33, distances[10], 0.0001)
    }

    @Test
    fun testDistances2() {
        val split = Split(10.0, 60.0)

        val distances = split.distances().splitDistances
        assertEquals(10, distances.size)
        assertEquals(1.0, distances[0], 0.0001)
        assertEquals(10.0, distances[9], 0.0001)
    }

    @Test
    fun testCalcSplitPace() {
        val split = Split(10.0, 60.0)
        
        val split0 = split.calcSplitPace(0, 6.0, 100, 1.0, 0.02)
        assertEquals(7.0, split0, 0.0001)

        val split1 = split.calcSplitPace(1, 6.0, 100, 1.0, 0.02)
        assertEquals(9.0, split1, 0.0001)

        val split2 = split.calcSplitPace(2, 6.0, 100, 0.33, 0.02)
        assertEquals(10.33, split2, 0.0001)
    }

    @Test
    fun testNegativeSplits() {
        val split = Split(10.33, 60.0, Distance.KILOMETERS, 10)

        val splits = split.negativeSplits(0.05).splitTimes

        assertEquals(6.07, splits[0].duration, 0.01)

        assertEquals(5.79, splits[5].duration, 0.01)        

        assertEquals(5.518, splits[10].duration, 0.01)
    }
}
