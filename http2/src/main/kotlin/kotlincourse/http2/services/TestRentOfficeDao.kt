package kotlincourse.http2.services

import kotlinCourse.http2.model.RentOffice
import org.springframework.stereotype.Service

@Service
class TestRentOfficeDao : RentOfficeDao {
    override fun getAll(): List<RentOffice> = listOf(
        RentOffice(1, "Kurskaya", "Moscow, Zemlyanoy val 25"),
        RentOffice(2, "Universitet", "Moscow, Pr-kt Vernadskogo 6"),
        RentOffice(3, "Savelovskaya", "Moscow, Burirskaya 46")
    )

    override fun getById(id: Int): RentOffice? =
        getAll().firstOrNull {
            it.id == id
        }
}