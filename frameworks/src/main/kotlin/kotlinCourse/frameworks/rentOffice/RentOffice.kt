package kotlinCourse.frameworks.rentOffice

import kotlinx.serialization.Serializable

@Serializable
data class RentOffice(
    val id: Int,
    val title: String,
    val address: String
)
