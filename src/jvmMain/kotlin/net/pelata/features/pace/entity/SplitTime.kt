package net.pelata.features.pace.entity

import kotlin.math.floor

const val SECONDS_IN_MINUTE = 60

class SplitTime(val duration: Double) {
    val minutes: Int
        get() = floor(duration).toInt()
    val seconds: Int
        get() = ((duration - floor(duration)) * SECONDS_IN_MINUTE).toInt()
}
