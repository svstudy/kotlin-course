package kotlinCourse.http.model

data class CarWithRentOffice(
    val id: Int,
    val vin: String,
    val model: String,
    val year: Int,
    val officeTitle: String,
    val officeAddress: String)
