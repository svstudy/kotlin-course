package kotlinCourse.database.entities

data class Customer(
    val id: Int,
    val fullName: String,
    val phone: String,
    val licenceNbr: String
)
