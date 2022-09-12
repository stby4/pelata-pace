package net.pelata.data

data class Footer(val year: Int)

data class Form(val distance: Double, val time: Double, val csrfToken: String = "")

data class Result(val average: Double, val distances: List<Double>, val splits: List<List<Double>>)
