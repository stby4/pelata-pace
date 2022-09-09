package net.pelata

import kotlin.math.*

// Contains the split calculations for a given distance and a goal time.
// precision: the pace will be calculated for each distance / precision.
class Split(val distance: Double, val time: Double, val precision : Int = 100) {

    // Calculates the average split time.
    // TODO assert distance != 0
    fun average(): Double = time / distance

    fun negativeSplits(percentage: Double): List<Pair<Double, Double>> {

        // assert(percentage >= 0.0 && percentage <= 1.0)

        val avg = average()
        val slowest = avg + avg * percentage
        val segments = ceil(distance).toInt()
        val distances = DoubleArray(segments) { 1.0 }
        distances[distances.size - 1] = distance - floor(distance)

        val step =  -2 * avg * percentage / (distance * precision)
        val times = distances.mapIndexed {idx, distance -> calcSplitPace(idx, slowest, precision, distance, step)}

        return distances zip times
    }

    fun calcSplitPace(idx: Int, slowest: Double, precision: Int, distance: Double, step: Double) : Double = slowest + (precision * step * (idx + distance/2))

}