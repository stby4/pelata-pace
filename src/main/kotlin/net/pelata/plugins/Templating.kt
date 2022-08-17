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
        get("/") {
            val date = LocalDate.now()
            val footerData = Footer(date.getYear())

            call.respond(FreeMarkerContent("index.ftl", mapOf("footer" to footerData)))
        }
    }
}

data class Footer(val year: Int)