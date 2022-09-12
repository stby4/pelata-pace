package net.pelata.data

import net.pelata.features.pace.data.SplitTime

data class Footer(val year: Int)

data class Form(val distance: Double, val time: Double, val csrfToken: String = "")

data class Result(val average: SplitTime, val distances: List<Double>, val splits: List<List<SplitTime>>)
