package kotlinCourse.frameworks

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

fun ApplicationCall.getId(): Int? = parameters["id"]?.toIntOrNull()

suspend fun ApplicationCall.okIfFound(found: Boolean) = respond(if (found) HttpStatusCode.OK else HttpStatusCode.NotFound)

suspend fun ApplicationCall.badRequest() = respond(HttpStatusCode.BadRequest)