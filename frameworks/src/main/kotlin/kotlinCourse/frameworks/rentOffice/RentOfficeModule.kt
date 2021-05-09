package kotlinCourse.frameworks.rentOffice

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinCourse.frameworks.badRequest
import kotlinCourse.frameworks.okIfFound
import kotlinCourse.frameworks.getId
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton

fun Application.rentOfficeModule() {
    val service: RentOfficeService by closestDI().instance()

    routing {
        route("/rentOffice") {
            get {
                call.respond(service.findAll())
            }
            get("/{id}") {
                val id = call.getId() ?: return@get call.badRequest()
                val rentOffice = service.findById(id)
                call.respond(rentOffice ?: HttpStatusCode.NotFound)
            }
            post {
                val request = call.receive<RentOfficeRequest>()
                call.respond(service.create(request.title, request.address))
            }
            put("/{id}")     {
                val id = call.getId() ?: return@put call.badRequest()
                val request = call.receive<RentOfficeRequest>()
                val count = service.update(RentOffice(id, request.title, request.address))
                call.okIfFound(count > 0)
            }
            delete("/{id}") {
                val id = call.getId() ?: return@delete call.badRequest()
                val count = service.delete(id)
                call.okIfFound(count > 0)
            }
        }
    }
}

fun DI.Builder.rentOfficeComponents() {
    bind<RentOfficeDao>() with singleton { RentOfficeDao(instance()) }
    bind<RentOfficeService>() with singleton { RentOfficeService(instance()) }
}

@Serializable
private data class RentOfficeRequest(
    val title: String,
    val address: String
)