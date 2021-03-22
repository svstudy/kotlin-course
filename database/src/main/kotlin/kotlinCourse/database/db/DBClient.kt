package kotlinCourse.database.db

import kotlinCourse.database.utils.DataException
import kotlinCourse.database.utils.Logger
import java.sql.*

class DBClient(private val connString: String, private val logger: Logger) {

    fun execute(sql: String): Boolean {
        try {
            DriverManager.getConnection(connString).use { conn ->
                conn.createStatement().use { st ->
                    st.execute(sql)
                    return true
                }
            }
        } catch (ex: SQLException) {
            logger.Error("${ex.message} ${ex.stackTrace}")
            return false
        }
        catch (ex: SQLTimeoutException) {
            logger.Error("${ex.message} ${ex.stackTrace}")
            return false
        }
    }

    @Throws(DataException::class)
    fun <T> executeQuery(sql: String, rowMapper: (ResultSet) -> T): List<T> = executeQuery(sql, { _ -> }, rowMapper)

    @Throws(DataException::class)
    fun <T> executeQuery(sql: String, setParams: (PreparedStatement) -> Unit, rowMapper: (ResultSet) -> T): List<T> {
        try {
            DriverManager.getConnection(connString).use {conn ->
                conn.prepareStatement(sql).use { st ->
                    setParams(st)
                    st.executeQuery().use { rs ->
                        val result = mutableListOf<T>()
                        while (rs.next()) {
                            result.add(rowMapper(rs))
                        }
                        return result
                    }
                }
            }
        } catch (ex: SQLException) {
            logger.Error("${ex.message} ${ex.stackTrace}")
            throw DataException(ex.message, ex)
        }
        catch (ex: SQLTimeoutException) {
            logger.Error("${ex.message} ${ex.stackTrace}")
            throw DataException(ex.message, ex)
        }
    }
}