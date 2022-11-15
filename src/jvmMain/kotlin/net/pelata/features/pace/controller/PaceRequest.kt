package net.pelata.features.pace.controller

import io.konform.validation.*
import io.konform.validation.jsonschema.*
import net.pelata.units.Distance

const val MIN_DISTANCE = 1.0
const val MAX_DISTANCE = 1000.0

const val MIN_TIME = 1.0
const val MAX_TIME = 10000.0

data class PaceRequest(val distance: Double, val time: Double)

val validatePaceRequest =
        Validation<PaceRequest> {
            PaceRequest::distance required
                    {
                        minimum(MIN_DISTANCE)
                        maximum(MAX_DISTANCE)
                    }

            PaceRequest::time required
                    {
                        minimum(MIN_TIME)
                        maximum(MAX_TIME)
                    }
        }
