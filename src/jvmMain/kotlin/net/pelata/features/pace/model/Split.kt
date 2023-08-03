package net.pelata.features.pace.model

import net.pelata.features.pace.data.SplitTime
import net.pelata.units.Distance
import kotlin.math.ceil
import kotlin.math.floor

const val MILES_IN_KILOMETERS = 1.609344

// Contains the split calculations for a given distance and a goal time.
// precision: the pace will be calculated for each distance / precision.
class Split(
    val distance: Double,
    val time: Double,
    val unit: Distance = Distance.KILOMETERS,
    val precision: Int = 100
) {
    val averagePace: Double by lazy { time / distance }
    val averageSpeed: Double by lazy { distance / (time / 60) }
    val averageSpeedKmh: Double by lazy {
        if (unit == Distance.KILOMETERS) averageSpeed else averageSpeed * MILES_IN_KILOMETERS
    }

    fun negativeSplits(percentage: Double): List<SplitTime> {
        assert(percentage >= 0.0 && percentage <= 1.0)

        val diff = averagePace * percentage
        val segmentsZero = ceil(distance).toInt() - 1

        val splitTimes =
            buildList() {
                for (i in 0..segmentsZero) {
                    add(calcSplitPace(i, diff, averagePace, distance))
                }
            }

        return splitTimes
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
        diff: Double,
        averagePace: Double,
        totalDistance: Double
    ): SplitTime {
        val splitDistance =
            when (idx < totalDistance - 1) {
                true -> 1.0
                false -> totalDistance - idx - 1
            }

        val duration = -2 * diff / totalDistance * (idx + splitDistance / 2) + averagePace + diff
        val split = SplitTime(duration)
        return split
    }
}
