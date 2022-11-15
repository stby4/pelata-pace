package net.pelata.features.pace.controller

import io.konform.validation.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import net.pelata.features.pace.controller.Form
import net.pelata.features.pace.controller.PaceRequest
import net.pelata.features.pace.controller.Result
import net.pelata.features.pace.entity.SplitTime
import net.pelata.features.pace.controller.validatePaceRequest
import net.pelata.features.pace.model.Split
import net.pelata.units.Distance
import kotlin.math.floor
import kotlin.math.ceil

const val DEFAULT_DISTANCE = 5.0
const val IS_FAST_THRESHOLD = 18.0

@Suppress("LongMethod")
fun Application.paceEndpoint() {

    routing() {

        val content =
                mutableMapOf<String, Any?>(
                        "form" to null,
                        "result" to null
                )

        route("/pace") {
            get {
                val formData = Form(DEFAULT_DISTANCE)
                content.put("form", formData)

                call.respond(FreeMarkerContent("index.ftl", content))
            }
        }

        @Suppress("TooGenericExceptionCaught")
        route("/pace/result") {
            get {
                try {
                    val distance =
                            (call.request.queryParameters["distance"]!!.filterNot { it == ',' })
                                    .toDouble()
                    val time =
                            (call.request.queryParameters["time"]!!.filterNot { it == ',' })
                                    .toDouble()
                    val unit = Distance.valueOf(call.request.queryParameters["unit"] ?: Distance.KILOMETERS.name)

                    val hours = if(time > 60.0) floor(time/60).toInt() else 0
                    val minutes = floor(time - hours * 60).toInt()
                    val seconds = ceil((time - floor(time)) * 60).toInt()

                    val params = PaceRequest(distance, time)
                    val validationResult = validatePaceRequest(params)

                    if (validationResult is Valid) {
                        val split = Split(distance, time, unit)
                        val splits = split.negativeSplitsList()

                        val distances = split.distances()
                        val averagePace = SplitTime(split.averagePace)
                        val averageSpeed = split.averageSpeed
                        val isFast = split.averageSpeedKmh > IS_FAST_THRESHOLD

                        val resultData =
                                Result(unit, averagePace, averageSpeed, distances, splits, isFast)

                        val formData = Form(distance, hours, minutes, seconds, unit)

                        content.put("form", formData)
                        content.put("result", resultData)

                        call.respond(FreeMarkerContent("result.ftl", content))
                    } else {
                        val errors = validationResult.errors
                        val errorMap = buildMap {
                            for (error in errors) {
                                put(error.dataPath, error.message)
                            }
                        }

                        val formData = Form(distance, hours, minutes, seconds, unit, errorMap)

                        content.put("form", formData)
                        
                        call.respond(FreeMarkerContent("result.ftl", content))
                    }
                } catch (e: NumberFormatException) {
                    throw RequestValidationException(e, listOf("Invalid request parameters."))
                } catch (e: Exception) {
                    throw RequestValidationException(e, listOf("Unknown error."))
                }
            }
        }
    }
}
