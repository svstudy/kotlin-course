package kotlinCourse.frameworks.car

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CarDao(private val database: Database) {
    fun findAll() = transaction(database) {
        CarTable.selectAll().map(::extractCar)
    }

    fun findById(id: Int): Car? = transaction(database) {
        CarTable.select { CarTable.id eq id }.map(::extractCar).firstOrNull()
    }

    fun create(vin: String, model: String, year: Int, rentOfficeId: Int): Car = transaction(database) {
        val id = CarTable.insertAndGetId {
            it[CarTable.vin] = vin
            it[CarTable.model] = model
            it[CarTable.year] = year
            it[CarTable.rentOfficeId] = rentOfficeId
        }

        Car(id.value, vin, model, year, rentOfficeId)
    }

    fun update(car: Car): Int = transaction(database) {
        CarTable.update({ CarTable.id eq car.id}) {
            it[vin] = car.vin
            it[model] = car.model
            it[year] = car.year
            it[rentOfficeId] = car.rentOfficeId
        }
    }

    fun updateRentOfficeId(carId: Int, rentOfficeId: Int): Int = transaction(database) {
        CarTable.update({ CarTable.id eq carId}) {
            it[CarTable.rentOfficeId] = rentOfficeId
        }
    }

    fun delete(id: Int): Int = transaction(database) {
        CarTable.deleteWhere { CarTable.id eq id}
    }

    private fun extractCar(row: ResultRow): Car = Car(
        row[CarTable.id].value,
        row[CarTable.vin],
        row[CarTable.model],
        row[CarTable.year],
        row[CarTable.rentOfficeId]
    )
}