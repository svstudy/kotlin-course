package kotlinCourse.database.db

import kotlinCourse.database.entities.*
import kotlinCourse.database.utils.DataException
import java.math.BigDecimal
import java.sql.ResultSet

class DBService(private val dbClient: DBClient) {

    @Throws(DataException::class)
    fun getCarById(id: Int): Car? {
        return dbClient.executeQuery("SELECT * FROM car WHERE id = ?", { st ->
            st.setInt(1, id)
        }, ::mapCar).firstOrNull()
    }

    @Throws(DataException::class)
    fun getCarsNotOlderThan(year: Int): List<Car> {
        return dbClient.executeQuery("SELECT * FROM car WHERE year >= ?", { st ->
            st.setInt(1, year)
        }, ::mapCar)
    }

    @Throws(DataException::class)
    fun getVinWithOfficeTitle(): List<Pair<String, String>> {
        return dbClient.executeQuery("""
            SELECT c.vin, ro.title
            FROM car c
            INNER JOIN rent_office ro on ro.id = c.rent_office_id
            """
        ) { rs ->
            Pair(rs.getString(1), rs.getString(2))
        }
    }

    @Throws(DataException::class)
    fun getCustomersWithTotalSpending(): List<SpendingByCustomer> {
        return dbClient.executeQuery("""
            SELECT cst.id as customer_id, cst.full_name as customer_full_name, SUM(coalesce((r.price), 0)) as total_spending
            FROM customer cst
            LEFT JOIN car_rent r on cst.id = r.customer_id
            GROUP BY cst.id
            """,
            ::mapSpendingByCustomer
        )
    }

    @Throws(DataException::class)
    fun getCarsByYearDescending(): List<Car> {
        return dbClient.executeQuery("SELECT * FROM car ORDER BY year DESC", ::mapCar)
    }

    private fun mapCar(rs: ResultSet) = Car(
        rs.getInt("id"),
        rs.getString("vin"),
        rs.getString("model"),
        rs.getInt("year"),
        rs.getInt("rent_office_id")
    )

    private fun mapSpendingByCustomer(rs: ResultSet) = SpendingByCustomer(
        rs.getInt("customer_id"),
        rs.getString("customer_full_name"),
        BigDecimal.valueOf(rs.getInt("total_spending") / 100.0),
    )

}