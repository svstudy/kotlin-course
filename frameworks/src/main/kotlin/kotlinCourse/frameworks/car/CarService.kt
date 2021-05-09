package kotlinCourse.frameworks.car

import java.sql.SQLException
import kotlin.jvm.Throws

class CarService(private val dao: CarDao) {
    fun findAll(): List<Car> = dao.findAll()

    fun findById(id: Int): Car? = dao.findById(id)

    fun create(vin: String, model: String, year: Int, rentOfficeId: Int): Car =
        dao.create(vin, model, year, rentOfficeId)

    fun update(car: Car): Int = dao.update(car)

    fun delete(id: Int): Int = dao.delete(id)

    @Throws(IllegalArgumentException::class)
    fun moveToRentOffice(carId: Int, rentOfficeId: Int): Int {
        try {
            return dao.updateRentOfficeId(carId, rentOfficeId)
        } catch (e: SQLException) {
            if (e.cause is org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException)
                throw IllegalArgumentException(e)
            else throw e
        }
    }
}