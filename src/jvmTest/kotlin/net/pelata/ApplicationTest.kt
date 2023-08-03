package net.pelata

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.application
import io.ktor.server.testing.testApplication
import net.pelata.features.pace.controller.paceEndpoint
import net.pelata.plugins.configureHTTP
import net.pelata.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertEquals

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
