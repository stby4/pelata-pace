package net.pelata.features.pace.model

import kotlin.math.*
import net.pelata.features.pace.entity.SplitDistanceList
import net.pelata.features.pace.entity.SplitTime
import net.pelata.features.pace.entity.SplitTimeList
import net.pelata.features.pace.entity.SplitTimeTable
import net.pelata.units.Distance

const val MILES_IN_KILOMETERS = 1.609344

// Contains the split calculations for a given distance and a goal time.
// precision: the pace will be calculated for each distance / precision.
class Split(
        val distance: Double,
        val time: Double,
        val unit: Distance = Distance.KILOMETERS,
        val splitLength: Int = 1,
        val precision: Int = 100
) {
    private val precisionCorrected = precision * splitLength
    val averagePace: Double by lazy { time / distance }
    val averageSpeed: Double by lazy { distance / (time / 60) }
    val averageSpeedKmh: Double by lazy {
        if (unit == Distance.KILOMETERS) averageSpeed else averageSpeed * MILES_IN_KILOMETERS
    }

    /** Calculates negative splits with a given percentage. */
    fun negativeSplits(percentage: Double): SplitTimeList {

        assert(percentage >= 0.0 && percentage <= 1.0)

        val slowest = averagePace + averagePace * percentage
        val segments = ceil(distance / splitLength).toInt()
        val distances = DoubleArray(segments) { splitLength.toDouble() }
        if (distance - floor(distance) > 0) {
            distances[distances.size - 1] = distance - floor(distance)
        }

        val step = -2 * averagePace * percentage / (distance * precisionCorrected)
        val times =
                distances.mapIndexed { idx, distance ->
                    SplitTime(calcSplitPace(idx, slowest, distance, step))
                }

        return SplitTimeList(times)
    }

    /** Returns ten rows of negative splits with increasing difference. */
    fun negativeSplitsList(): SplitTimeTable {
        val splitTimeTable =
                buildList() {
                    for (i in 1..10) {
                        add(negativeSplits(i / 200.0))
                    }
                }

        return SplitTimeTable(splitTimeTable)
    }

    /**
     * Returns the cummulated distance sum for each split. For n splits, n - 1 will have a distance
     * of 1, and the last one has the remaining distance to the full track distance.
     */
    fun distances(): SplitDistanceList {
        val distances = DoubleArray(ceil(distance / splitLength).toInt()) { splitLength.toDouble() }
        if (distance - floor(distance) > 0) {
            distances[distances.size - 1] = distance - floor(distance)
        }

        val distancesSum = distances.mapIndexed { idx, distance -> splitLength * idx + distance }

        return SplitDistanceList(distancesSum)
    }

    /** Calculates the pace for a given split (defined by idx). */
    internal fun calcSplitPace(idx: Int, slowest: Double, distance: Double, step: Double): Double =
            slowest + (precisionCorrected * step * splitLength.toDouble() * (idx + distance / 2))
}
