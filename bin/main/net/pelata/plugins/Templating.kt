package net.pelata.plugins

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.mustache.Mustache
import io.ktor.server.mustache.MustacheContent
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }

    routing {
        get("/") {
            call.respond(MustacheContent("index.hbs", {}))
        }
    }
}