package kotlincourse.http2.api

import kotlinCourse.http2.model.RentOffice
import kotlincourse.http2.services.RentOfficeDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rentOffice")
class RentOfficeController(private val rentOfficeDao: RentOfficeDao) {
    @GetMapping
    fun getCars(): List<RentOffice> = rentOfficeDao.getAll()

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): RentOffice? {
        return rentOfficeDao.getById(id)
    }
}