package net.pelata.plugins

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import freemarker.cache.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.time.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
        val date = LocalDate.now()
        val footerData = Footer(date.getYear())

        val content = mutableMapOf<String, Any?>(
            "footer" to footerData,
            "form" to null,
        )

        route("/") {

            get {
                call.respond(FreeMarkerContent("index.ftl", content))
            }

            post {
                val formParameters = call.receiveParameters()
                val distance = formParameters["distance"]?.toDouble()

                when (distance) {
                    null -> call.respondRedirect("/")
                    else -> call.respondRedirect("/result?distance=$distance")
                }

                call.respond(FreeMarkerContent("result.ftl", content))
            }
        }

        route("/result") {
            get {
                val distance = call.request.queryParameters["distance"]?.toDouble()

                when(distance) {
                    null -> call.respondRedirect("/")
                    else -> {
                        val formData = Form(distance)
                        
                        content.put("form", formData)
                        call.respond(FreeMarkerContent("result.ftl", content))
                    }
                }
            }
        }
    }
}

data class Footer(val year: Int)
data class Form(val distance: Double)