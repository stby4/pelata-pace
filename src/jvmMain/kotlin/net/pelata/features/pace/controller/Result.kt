package net.pelata.features.pace.controller

import net.pelata.features.pace.entity.SplitTime
import net.pelata.features.pace.entity.SplitTimeTable
import net.pelata.features.pace.entity.SplitDistanceList
import net.pelata.units.Distance

data class Result(
    val unit: Distance,
    val average: SplitTime,
    val averageSpeed: Double,
    val distances: SplitDistanceList,
    val splits: SplitTimeTable,
    val isFast: Boolean
)
