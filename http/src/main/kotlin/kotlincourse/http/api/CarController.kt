package kotlinCourse.http.api

import io.swagger.annotations.ApiOperation
import kotlinCourse.http.configuration.RentOfficeServiceProperties
import kotlinCourse.http.model.Car
import kotlinCourse.http.model.CarWithRentOffice
import kotlinCourse.http.model.RentOffice
import kotlinCourse.http.services.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@RestController
@RequestMapping("/car")
class CarController(
    @Autowired private val carRepo: CarRepository,
    @Autowired private val rentOfficeServProps: RentOfficeServiceProperties
    ) {

    private val rentOfficeClient: WebClient = WebClient.create("http://" + rentOfficeServProps.url)

    @ApiOperation("Returns all cars")
    @GetMapping
    fun getCars(): List<Car> = carRepo.findAll()

    @ApiOperation("Returns car with rent office info by id")
    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): ResponseEntity<CarWithRentOffice> {
        val car = carRepo.findById(id).orElse(null) ?:
            return ResponseEntity.notFound().build()
        var office = getRentOffice(car.rentOfficeId) ?:
            return ResponseEntity.notFound().build()

        return ResponseEntity.of(Optional.of(
            CarWithRentOffice(car.id, car.vin, car.model, car.year, office.title, office.address)
        ))
    }

    private fun getRentOffice(id: Int): RentOffice? {
        return rentOfficeClient.get()
            .uri("rentOffice/$id")
            .retrieve()
            .bodyToMono(RentOffice::class.java)
            .block()
    }
}