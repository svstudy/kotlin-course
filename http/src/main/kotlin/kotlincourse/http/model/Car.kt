package kotlinCourse.http.model

data class Car(
    val id: Int,
    val vin: String,
    val model: String,
    val year: Int,
    val rentOfficeId: Int
)
