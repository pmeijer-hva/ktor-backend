package routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Book
import services.BookService

fun Route.books() {

    val bookService = BookService()

    get("books") {
        val allBooks = bookService.getAllBooks()
        call.respond(allBooks)
//
//        val user = User("user name", "user@example.com")
//        call.respond(FreeMarkerContent("hello.ftl", mapOf("user" to user)))

    }

    post("book") {
        val bookRequest = call.receive<Book>()
        bookService.addBook(bookRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("book/{id}") {
        val bookId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        bookService.deleteBook(bookId)
        call.respond(HttpStatusCode.OK)
    }
}