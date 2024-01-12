package net.pelata.features.pace.data

import net.pelata.units.Distance

data class Result(
    val unit: Distance,
    val average: SplitTime,
    val averageSpeed: Double,
    val distances: List<Double>,
    val splits: List<List<SplitTime>>,
    val isFast: Boolean,
)
