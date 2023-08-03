package net.pelata

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import net.pelata.features.pace.controller.paceEndpoint
import net.pelata.plugins.configureHTTP
import net.pelata.plugins.configureMonitoring
import net.pelata.plugins.configureRouting

fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT") ?: "8080").toInt()) {
        configureMonitoring()
        configureHTTP()
        configureRouting()
        paceEndpoint()
    }.start(wait = true)
}
