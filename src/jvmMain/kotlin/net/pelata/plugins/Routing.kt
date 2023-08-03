package net.pelata.plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.freemarker.FreeMarker
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

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
        staticResources("/static", "static") {
            exclude { url -> url.path.contains("txt") }
        }
    }
}
