package net.pelata.features.pace.controller

import freemarker.cache.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import java.security.*
import java.time.*
import net.pelata.data.*
import net.pelata.features.pace.data.SplitTime
import net.pelata.features.pace.model.*
import net.pelata.library.Security

fun Application.paceEndpoint() {

    fun getCsrfToken(): String {
        val token = SecureRandom().nextInt().toString()
        return token
    }

    routing() {
        val date = LocalDate.now()
        val footerData = Footer(date.getYear())

        val content =
                mutableMapOf<String, Any?>(
                        "footer" to footerData,
                        "form" to null,
                )

        route("/pace") {
            get {
                val csrfToken = getCsrfToken()
                val formData = Form(5.0, 30.0, csrfToken)
                content.put("form", formData)

                call.sessions.set(Security(csrfToken = csrfToken))
                call.respond(FreeMarkerContent("index.ftl", content))
            }

            post {
                val formParameters = call.receiveParameters()
                val distance = formParameters["distance"]?.toDouble()
                val time = formParameters["time"]?.toDouble()
                val formCsrf = formParameters["csrfToken"]

                val session = call.sessions.get<Security>() ?: Security(getCsrfToken())
                val sessionCsrf = session.csrfToken

                if ("" == sessionCsrf || formCsrf != sessionCsrf) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                if (null != distance && null != time) {
                    call.respondRedirect("/pace/result?distance=$distance&time=$time")
                } else {
                    call.respondRedirect("/pace")
                }
            }
        }

        route("/pace/result") {
            get {
                val distance = call.request.queryParameters["distance"]?.toDouble()
                val time = call.request.queryParameters["time"]?.toDouble()

                if (null != distance && null != time) {
                    val csrfToken = getCsrfToken()
                    call.sessions.set(Security(csrfToken = csrfToken))

                    val formData = Form(distance, time, csrfToken)

                    val split = Split(distance, time)
                    val splits =
                            buildList() {
                                for (i in 1..10) {
                                    add(split.negativeSplits(i / 100.0))
                                }
                            }

                    val distances = split.distances()
                    val average = SplitTime(split.average)

                    val resultData = Result(average, distances, splits)

                    content.put("form", formData)
                    content.put("result", resultData)
                    call.respond(FreeMarkerContent("result.ftl", content))
                } else {
                    call.respondRedirect("/")
                }
            }
        }
    }
}
