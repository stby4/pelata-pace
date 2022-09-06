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
    fun testNegativeSplits() {
        val split = Split(10.33, 60.0, 3)

        val splits = split.negativeSplits(5.0)

        assertEquals(1.0, splits[0].first, 0.0001)
        assertEquals(6.3, splits[0].second, 0.0001)


        assertEquals(0.33, splits[11].first, 0.0001)
        assertEquals(5.7, splits[11].second, 0.0001)
    }
}