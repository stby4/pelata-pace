package net.pelata.features.pace.data

import net.pelata.features.pace.data.PaceRequest

data class Form(val distance: Double, val time: Double, val errors: Map<String, String>? = null)
