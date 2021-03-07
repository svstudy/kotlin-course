package kotlinCourse.collections

import java.time.LocalDate

data class EmployeeWithPassport(
    val id: Int,
    val email: String,
    val role: String,
    val passNbr: String,
    val birthDate: LocalDate,
    val firstName: String,
    val secondName: String
)
