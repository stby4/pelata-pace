package net.pelata.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.gzip
import io.ktor.server.plugins.compression.minimumSize
import io.ktor.server.plugins.conditionalheaders.ConditionalHeaders
import io.ktor.server.plugins.defaultheaders.DefaultHeaders

const val DEFLATE_THRESHOLD: Long = 1024

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload")
    }
    install(ConditionalHeaders)
    install(Compression) {
        gzip { priority = 1.0 }
        deflate {
            priority = 10.0
            minimumSize(DEFLATE_THRESHOLD) // condition
        }
    }
}
