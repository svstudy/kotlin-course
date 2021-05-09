package kotlinCourse.frameworks.car

import org.jetbrains.exposed.dao.id.IntIdTable

object CarTable : IntIdTable("car") {
    val vin = text("vin")
    val model = text("model")
    val year = integer("year")
    val rentOfficeId  = integer("rent_office_id")
}