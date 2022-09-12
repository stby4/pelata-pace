package net.pelata

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.pelata.plugins.*
import net.pelata.features.pace.controller.*

fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT")?:"8080").toInt()) {
        configureMonitoring()
        configureHTTP()
        configureRouting()
        paceEndpoint()
    }.start(wait = true)
}