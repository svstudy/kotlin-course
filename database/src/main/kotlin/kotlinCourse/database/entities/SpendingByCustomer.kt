package kotlinCourse.database.entities

import java.math.BigDecimal

data class SpendingByCustomer(
    val customerId: Int,
    val customerFullName: String,
    val totalSpending: BigDecimal
)
