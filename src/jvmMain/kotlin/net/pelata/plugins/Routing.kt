package net.pelata.plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.FreeMarker
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.http.content.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.io.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            // call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
            call.respond(
                    HttpStatusCode.BadRequest,
                    FreeMarkerContent(
                            "error.ftl",
                            mapOf<String, String>("message" to cause.reasons.joinToString())
                    )
            )
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                    status,
                    FreeMarkerContent(
                            "error.ftl",
                            mapOf<String, Any>(
                                    "message" to "This site does not exist.",
                                    "status" to status
                            )
                    )
            )
        }
    }
    install(RequestValidation)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
        route("/") { get { call.respondRedirect("/pace", true) } }
        // Static plugin.
        staticFiles("/static", File("static")) {
            exclude { file -> file.path.contains("txt") }
        }
    }
}
