import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.gson.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import routes.apiRoute

@KtorExperimentalAPI
fun Application.module() {
    initDB()

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) { gson { } }

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Routing) {
        apiRoute()

        static("/static") {
            resources("static")
        }
    }

}

@KtorExperimentalAPI
fun main(args: Array<String>) {
    embeddedServer(Netty, 8080,
        watchPaths = listOf("ktor_test"),
        module = Application::module
    ).start()
}


