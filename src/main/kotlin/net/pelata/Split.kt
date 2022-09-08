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
        val diff = avg * percentage
        val slowest = avg + diff
        val fastest = avg - diff
        val segments = ceil(distance).toInt()
        val distances = DoubleArray(segments) { 1.0 }
        distances[distances.size - 1] = distance - floor(distance)

        // val interval = distance / precision
        val intervalDiff = -2.0 * avg / (percentage * distance * precision)

        print(distances)

        val times = distances.mapIndexed {idx, value -> if(1.0 == value) slowest + idx * intervalDiff * precision / 2 else fastest - ((value / precision) / 2) * intervalDiff}

        print(times)

        return distances zip times
    }
}