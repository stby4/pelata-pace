package net.pelata.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import java.time.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.freemarker.*
import freemarker.cache.*
import io.ktor.util.*
import net.pelata.library.*

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(StatusPages) {
        exception<AuthenticationException> { call, _ ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, _ ->
            call.respond(HttpStatusCode.Forbidden)
        }
    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    

    routing {
        route("/") {
            get {
                call.respondRedirect("/pace")
            }
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
