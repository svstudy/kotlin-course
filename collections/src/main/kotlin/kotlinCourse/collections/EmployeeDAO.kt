package kotlinCourse.collections

class EmployeeDAO {
    private val data: List<Employee> = listOf(
        Employee(1, "dart.vader@empire.com", "Sith Lord"),
        Employee(2, "luke.skywalker@rebels.com", "Jedi"),
        Employee(3, "chewie@rebels.com", "co-pilot"),
        Employee(4, "big.boss@empire.com", "Sith Lord"),
        Employee(5, "obi1@rebels.com", "Jedi"),
    )

    fun getAll(): List<Employee> = data

    fun getById(id: Int) = data.find { it.id == id }
}