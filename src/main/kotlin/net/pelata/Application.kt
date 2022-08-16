package net.pelata

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.pelata.plugins.*

fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT")?:"8080").toInt()) {
        configureTemplating()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
