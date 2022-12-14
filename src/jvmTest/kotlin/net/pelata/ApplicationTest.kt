package net.pelata

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.*
import io.ktor.server.request.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.conditionalheaders.*
import io.ktor.server.plugins.compression.*
import java.time.*
import io.ktor.server.sessions.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import net.pelata.plugins.*
import net.pelata.features.pace.controller.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureHTTP()
            configureRouting()
            paceEndpoint()
        }
        client.get("/pace").apply {
            assertEquals(HttpStatusCode.OK, status)
            // assertEquals("Hello World!", bodyAsText())
        }
        // client.get("/").apply {
        //     assertEquals(HttpStatusCode.MovedPermanently, status)
        // }
        client.get("/pace?time=30.5&distance=120.78").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/pace?time=30.5&distance=120.78").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        // client.get("/pace?time=10000.1&distance=1000").apply {
        //     assertEquals(HttpStatusCode.BadRequest, status)
        // }
        // client.get("/pace?time=10000&distance=1000.1").apply {
        //     assertEquals(HttpStatusCode.BadRequest, status)
        // }
        client.get("/pace?time=10,000&distance=1,000").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
