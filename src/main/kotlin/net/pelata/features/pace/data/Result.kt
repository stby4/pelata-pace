package net.pelata.features.pace.data

import net.pelata.features.pace.data.SplitTime

data class Result(
    val average: SplitTime,
    val averageSpeed: Double,
    val distances: List<Double>,
    val splits: List<List<SplitTime>>

    )
