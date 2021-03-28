package kotlinCourse.database.entities

import java.math.BigDecimal
import java.time.LocalDateTime

data class CarRent(
    val car_id: Int,
    val customer_id: Int,
    val startDateTime: LocalDateTime,
    val days: Int,
    val price: BigDecimal
)
