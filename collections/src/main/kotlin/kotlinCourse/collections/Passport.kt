package kotlinCourse.collections

import java.time.LocalDate

data class Passport(
    val ownerId: Int,
    val passNbr: String,
    val birthDate: LocalDate,
    val firstName: String,
    val secondName: String
)
