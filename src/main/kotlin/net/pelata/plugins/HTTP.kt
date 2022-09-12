package net.pelata.plugins

import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.conditionalheaders.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

const val DEFLATE_THRESHOLD : Long = 1024

fun Application.configureHTTP() {
    install(DefaultHeaders)
    install(ConditionalHeaders)
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(DEFLATE_THRESHOLD) // condition
        }
    }

}
