package net.pelata.plugins

import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import freemarker.cache.*
import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.security.*
import java.time.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Sessions) {
        val secretSignKey = hex(System.getenv("SIGN_KEY")?:"6819b57a326945c1968f45236589")
        
        cookie<Security>("SECURITY") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60 * 60 * 2 // 2 hours
            cookie.extensions["Secure"] = "true"
            transform(SessionTransportTransformerMessageAuthentication(secretSignKey))
        }
    }

    fun getCsrfToken(): String {
        val token = SecureRandom().nextInt().toString()
        return token
    }

    routing {
        val date = LocalDate.now()
        val footerData = Footer(date.getYear())

        val content = mutableMapOf<String, Any?>(
            "footer" to footerData,
            "form" to null,
        )

        route("/") {

            get {
                val csrfToken = getCsrfToken()  
                val formData = Form(5.0, csrfToken)
                content.put("form", formData)
                        
                call.sessions.set(Security(csrfToken = csrfToken))
                call.respond(FreeMarkerContent("index.ftl", content))
            }

            post {
                val formParameters = call.receiveParameters()
                val distance = formParameters["distance"]?.toDouble()
                val formCsrf = formParameters["csrfToken"]
                
                val session = call.sessions.get<Security>() ?: Security(getCsrfToken())
                val sessionCsrf = session.csrfToken

                if ("" == sessionCsrf || formCsrf != sessionCsrf) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                when (distance) {
                    null -> call.respondRedirect("/")
                    else -> call.respondRedirect("/result?distance=$distance")
                }
            }
        }

        route("/result") {
            get {
                val distance = call.request.queryParameters["distance"]?.toDouble()

                when(distance) {
                    null -> call.respondRedirect("/")
                    else -> {
                        val csrfToken = getCsrfToken()
                        call.sessions.set(Security(csrfToken = csrfToken))

                        val formData = Form(distance, csrfToken)
                        
                        content.put("form", formData)
                        call.respond(FreeMarkerContent("result.ftl", content))
                    }
                }
            }
        }
    }
}

data class Footer(val year: Int)
data class Form(val distance: Double, val csrfToken: String = "")
data class Security(val csrfToken: String = "")