package net.pelata

import kotlin.test.*

class SplitTest {

    @Test
    fun testAverage() {
        val split = Split(10.0, 60.0)
        val avg = split.average()

        assertEquals(6.0, avg, 0.0001)
    }

    @Test
    fun testCalcSPlitPace() {
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
        val split = Split(10.33, 60.0, 10)

        val splits = split.negativeSplits(0.05)

        assertEquals(1.0, splits[0].first, 0.0001)
        assertEquals(6.07, splits[0].second, 0.01)

        assertEquals(1.0, splits[5].first, 0.0001)
        assertEquals(5.79, splits[5].second, 0.01)        

        assertEquals(0.33, splits[10].first, 0.0001)
        assertEquals(5.518, splits[10].second, 0.01)
    }
}