package kotlinCourse.collections

fun main() {
    val service = EmployeeWithPassportService(EmployeeDAO(), PassportDAO())

    printInfo(EmployeeWithPassportService::getAll.name,
        service.getAll())
    printInfo(EmployeeWithPassportService::sortedByBirthDate.name,
        service.sortedByBirthDate())
    printInfo(EmployeeWithPassportService::groupByRole.name,
        service.groupByRole())
    printInfo(EmployeeWithPassportService::count.name) {
        println(service.count { e -> e.email.endsWith("rebels.com") })
    }
}

fun printInfo(name: String, result: List<EmployeeWithPassport>) {
    printInfo(name) {
        println(result.joinToString(separator = System.lineSeparator()))
    }
}

fun printInfo(name: String, result: Map<String, List<EmployeeWithPassport>>) {
    printInfo(name) {
        for ((role, group) in result) {
            println("$role:")
            println(group.joinToString(separator = System.lineSeparator()))
        }
    }
}

fun printInfo(name: String, action: () -> Unit) {
    println("$name():")
    action()
    println()
}