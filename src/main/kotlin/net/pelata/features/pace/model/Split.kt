package net.pelata.features.pace.model

import kotlin.math.*
import net.pelata.features.pace.data.SplitTime

// Contains the split calculations for a given distance and a goal time.
// precision: the pace will be calculated for each distance / precision.
class Split(val distance: Double, val time: Double, val precision: Int = 100) {
    val averagePace: Double = time / distance
    val averageSpeed: Double = distance / (time / 60)

    fun negativeSplits(percentage: Double): List<SplitTime> {

        assert(percentage >= 0.0 && percentage <= 1.0)

        val slowest = averagePace + averagePace * percentage
        val segments = ceil(distance).toInt()
        val distances = DoubleArray(segments) { 1.0 }
        if (distance - floor(distance) > 0) {
            distances[distances.size - 1] = distance - floor(distance)
        }

        val step = -2 * averagePace * percentage / (distance * precision)
        val times =
                distances.mapIndexed { idx, distance ->
                    SplitTime(calcSplitPace(idx, slowest, precision, distance, step))
                }

        return times
    }

    fun distances(): List<Double> {
        val distances = DoubleArray(ceil(distance).toInt()) { 1.0 }
        if (distance - floor(distance) > 0) {
            distances[distances.size - 1] = distance - floor(distance)
        }

        val distancesSum = distances.mapIndexed { idx, distance -> 1.0 * idx + distance }

        return distancesSum
    }

    fun calcSplitPace(
            idx: Int,
            slowest: Double,
            precision: Int,
            distance: Double,
            step: Double
    ): Double = slowest + (precision * step * (idx + distance / 2))
}
