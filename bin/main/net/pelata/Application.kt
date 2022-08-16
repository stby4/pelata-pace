package net.pelata

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.pelata.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureTemplating()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
