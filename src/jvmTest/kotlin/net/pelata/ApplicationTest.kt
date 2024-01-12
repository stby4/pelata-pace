package net.pelata

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.application
import io.ktor.server.testing.testApplication
import net.pelata.features.pace.controller.paceEndpoint
import net.pelata.plugins.configureHTTP
import net.pelata.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
            application {
                configureHTTP()
                configureRouting()
                paceEndpoint()
            }
            client.get("/pace").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertContains(bodyAsText(), "<h1 class=\"title\">Pelata</h1>")
            }
            // client.get("/").apply {
            //     assertEquals(HttpStatusCode.MovedPermanently, status)
            // }
            client.get("/pace/result") {
                url {
                    parameters.append("distance", "120.78")
                    parameters.append("time", "30.5")
                    parameters.append("unit", "KILOMETERS")
                }
            }.apply {
                assertEquals(HttpStatusCode.OK, status)
                assertContains(bodyAsText(), "That's equal to an incredible speed of 237.6")
            }
            client.get("/pace/result") {
                url {
                    parameters.append("distance", "10000.1")
                    parameters.append("time", "1000")
                    parameters.append("unit", "MILES")
                }
            }.apply {
                assertEquals(HttpStatusCode.OK, status)
                assertContains(bodyAsText(), "Must be at most '1000.0'.")
            }
            client.get("/pace/result") {
                url {
                    parameters.append("distance", "0")
                    parameters.append("time", "0")
                    parameters.append("unit", "MILES")
                }
            }.apply {
                assertEquals(HttpStatusCode.OK, status)
                assertContains(bodyAsText(), "Must be at least '1.0'.")
            }
            client.get("/pace/result") {
                url {
                    parameters.append("distance", "1")
                    parameters.append("time", "1")
                    parameters.append("unit", "FEET")
                }
            }.apply {
                assertEquals(HttpStatusCode.BadRequest, status)
                assertContains(bodyAsText(), "Ooops")
            }
            client.post("/pace?time=10,000&distance=1,000").apply {
                assertEquals(HttpStatusCode.MethodNotAllowed, status)
            }
        }
}
