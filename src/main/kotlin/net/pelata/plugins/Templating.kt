package net.pelata.plugins

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import freemarker.cache.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
        get("/") {
            call.respond(FreeMarkerContent("index.ftl", {}))
        }
    }
}
