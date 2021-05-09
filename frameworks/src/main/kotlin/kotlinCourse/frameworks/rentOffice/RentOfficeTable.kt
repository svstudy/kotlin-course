package kotlinCourse.frameworks.rentOffice

import org.jetbrains.exposed.dao.id.IntIdTable

object RentOfficeTable : IntIdTable("rent_office") {
    val title = text("title")
    val address = text("address")
}