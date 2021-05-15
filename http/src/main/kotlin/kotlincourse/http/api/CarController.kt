package kotlincourse.http.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import kotlinCourse.http.model.Car
import kotlincourse.http.model.CarWithRentOffice
import kotlincourse.http.model.RentOffice
import kotlincourse.http.services.CarDao
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import java.util.Optional

@RestController
@RequestMapping("/car")
class CarController(private val carDao: CarDao) {
    private val webClient: WebClient = WebClient.create("http://localhost:9090")

    @ApiOperation("Returns all cars")
    @GetMapping
    fun getCars(): List<Car> = carDao.getAll()

    @ApiOperation("Returns car with rent office info by id")
    @ApiResponses(
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 200, message = "OK", response = CarWithRentOffice::class)
    )
    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): ResponseEntity<CarWithRentOffice> {
        val car = carDao.getById(id) ?:
            return ResponseEntity.notFound().build()
        var office = getRentOffice(car.rentOfficeId) ?:
            return ResponseEntity.notFound().build()

        return ResponseEntity.of(Optional.of(
            CarWithRentOffice(car.id, car.vin, car.model, car.year, office.title, office.address)
        ))
    }

    @ApiOperation("Adds a new car")
    @PostMapping
    fun addCar(@RequestBody car: Car) {
        println("Car was added. Car = $car")
    }

    @ApiOperation("Updates a car by id")
    @PutMapping("/{id}")
    fun updateCar(@PathVariable id: Int, @RequestBody car: Car) {
        println("Car was updated. Car = $car, id = $id")
    }

    @ApiOperation("Deletes a car by id")
    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Int) {
        println("Car was deleted with id = $id")
    }

    private fun getRentOffice(id: Int): RentOffice? {
        return webClient.get()
            .uri("rentOffice/$id")
            .retrieve()
            .bodyToMono(RentOffice::class.java)
            .block()
    }
}