package kotlinCourse.frameworks.rentOffice

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class RentOfficeDao(private val database: Database) {
    fun findAll() = transaction(database) {
        RentOfficeTable.selectAll().map(::extractRentOffice)
    }

    fun findById(id: Int): RentOffice? = transaction(database) {
        RentOfficeTable.select { RentOfficeTable.id eq id }.map(::extractRentOffice).firstOrNull()
    }

    fun create(title: String, address: String): RentOffice = transaction(database) {
        val id = RentOfficeTable.insertAndGetId {
            it[RentOfficeTable.title] = title
            it[RentOfficeTable.address] = address
        }

        RentOffice(id.value, title, address)
    }

    fun update(rentOffice: RentOffice): Int = transaction(database) {
        RentOfficeTable.update({ RentOfficeTable.id eq rentOffice.id}) {
            it[title] = rentOffice.title
            it[address] = rentOffice.address
        }
    }

    fun delete(id: Int): Int = transaction(database) {
        RentOfficeTable.deleteWhere { RentOfficeTable.id eq id}
    }

    private fun extractRentOffice(row: ResultRow): RentOffice = RentOffice(
        row[RentOfficeTable.id].value,
        row[RentOfficeTable.title],
        row[RentOfficeTable.address]
    )
}