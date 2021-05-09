package kotlinCourse.frameworks.car

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinCourse.frameworks.badRequest
import kotlinCourse.frameworks.okIfFound
import kotlinCourse.frameworks.getId
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton

fun Application.carModule() {
    val service: CarService by closestDI().instance()

    routing {
        route("/car") {
            get {
                call.respond(service.findAll())
            }

            get("/{id}") {
                val id = call.getId() ?: return@get call.badRequest()
                val car = service.findById(id)
                call.respond(car ?: HttpStatusCode.NotFound)
            }

            post {
                val request = call.receive<CarRequest>()
                call.respond(service.create(request.vin, request.model, request.year, request.rentOfficeId))
            }

            put("/{id}")     {
                val id = call.getId() ?: return@put call.badRequest()
                val request = call.receive<CarRequest>()
                val count = service.update(Car(id, request.vin, request.model, request.year, request.rentOfficeId))
                call.okIfFound(count > 0)
            }

            put("/{id}/moveToOffice/{officeId}")     {
                val id = call.getId() ?: return@put call.badRequest()
                val officeId = call.parameters["officeId"]?.toIntOrNull() ?: return@put call.badRequest()
                try {
                    val count = service.moveToRentOffice(id, officeId)
                    call.okIfFound(count > 0)
                }
                catch (e: IllegalArgumentException) {
                    call.badRequest()
                }
            }

            delete("/{id}") {
                val id = call.getId() ?: return@delete call.badRequest()
                val count = service.delete(id)
                call.okIfFound(count > 0)
            }
        }
    }
}

fun DI.Builder.carComponents() {
    bind<CarDao>() with singleton { CarDao(instance()) }
    bind<CarService>() with singleton { CarService(instance()) }
}

@Serializable
private data class CarRequest(
    val vin: String,
    val model: String,
    val year: Int,
    val rentOfficeId: Int
)