package net.pelata.plugins

import freemarker.cache.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.time.*

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(StatusPages) {
        // exception<AuthenticationException> { call, _ ->
        //     call.respond(HttpStatusCode.Unauthorized, FreeMarkerContent("error.ftl",
        // mapOf<String, String>("message" to "You are not authenticated to view this page.")))
        // }
        // exception<AuthorizationException> { call, _ ->
        //     call.respond(HttpStatusCode.Forbidden, FreeMarkerContent("error.ftl", mapOf<String,
        // String>("message" to "You are not authorised to view this page.")))
        // }
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
        // Static plugin. Try to access `/static/index.html`
        static("/static") { resources("static") }
    }
}

class AuthenticationException : RuntimeException()

class AuthorizationException : RuntimeException()
