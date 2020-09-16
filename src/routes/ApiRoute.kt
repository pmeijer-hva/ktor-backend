package routes

import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.apiRoute() {
    route("/api/v1") {
        books()
    }

    route("/") {
        get {
            val user = User("Pimmie", "pim@pim.nl")

            call.respond(FreeMarkerContent("hello.ftl", mapOf("user" to user)))
        }
    }
}

data class User(val name: String, val email: String)
