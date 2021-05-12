package kotlinCourse.http2.api

import kotlinCourse.http2.model.RentOffice
import kotlinCourse.http2.services.RentOfficeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rentOffice")
class RentOfficeController(@Autowired private val rentOfficeRepo: RentOfficeRepository) {
    @GetMapping
    fun getCars(): List<RentOffice> = rentOfficeRepo.findAll()

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): RentOffice? {
        return rentOfficeRepo.findById(id).orElse(null)
    }
}