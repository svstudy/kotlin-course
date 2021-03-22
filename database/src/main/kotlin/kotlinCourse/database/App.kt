package kotlinCourse.database

import kotlinCourse.database.db.*
import kotlinCourse.database.utils.ConsoleLogger
import kotlinCourse.database.utils.DataException
import kotlinCourse.database.utils.LogLevel

fun main() {
    // RentOffice -> Car (1:N)
    // Car -> Customer (N:N using CarRent)

    val logger = ConsoleLogger(LogLevel.ERROR)
    val client = DBClient(Config.connectionString, logger)
    val initializer = DBInitializer(client, logger)

    if (!initializer.initSchema()) return
    if (!initializer.insertTestData()) return

    try {
        val dbService = DBService(client)

        val carById = dbService.getCarById(1)
        printInfo("getCarById(1)") {
            println(carById)
        }

        val carsGE2019 = dbService.getCarsNotOlderThan(2019)
        printInfo("getCarsNotOlderThan(2019)", carsGE2019)

        val vinWithRentOfficeTitle = dbService.getVinWithOfficeTitle()
        printInfo("getVinWithOfficeTitle()", vinWithRentOfficeTitle)

        val customersWithTotalSpending = dbService.getCustomersWithTotalSpending()
        printInfo("getCustomersWithTotalSpending()", customersWithTotalSpending)

        val carsByYear = dbService.getCarsByYearDescending()
        printInfo("getCarsByYearDescending()", carsByYear)
    } catch (ex: DataException) {
        logger.Error("${ex.message} ${ex.stackTrace}")
        throw ex
    } finally {
        initializer.clearSchema()
    }

}

fun <T> printInfo(name: String, result: List<T>) {
    printInfo(name) {
        println(result.joinToString(separator = System.lineSeparator()))
    }
}

fun printInfo(name: String, action: () -> Unit) {
    println("$name:")
    action()
    println()
}