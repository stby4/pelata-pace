package net.pelata.features.pace.data

import kotlin.math.*

class SplitTime(val duration: Double) {
    val minutes: Int
        get() = floor(duration).toInt()
    val seconds: Int
        get() = ((duration - floor(duration)) * 60).toInt()
}