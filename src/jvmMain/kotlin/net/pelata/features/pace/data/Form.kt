package net.pelata.features.pace.data

import net.pelata.units.Distance

const val DEFAULT_HOURS = 0
const val DEFAULT_MINUTES = 30
const val DEFAULT_SECONDS = 0

data class Form(
    val distance: Double,
    val hours: Int = DEFAULT_HOURS,
    val minutes: Int = DEFAULT_MINUTES,
    val seconds: Int = DEFAULT_SECONDS,
    val unit: Distance = Distance.KILOMETERS,
    val errors: Map<String, String>? = null,
)
