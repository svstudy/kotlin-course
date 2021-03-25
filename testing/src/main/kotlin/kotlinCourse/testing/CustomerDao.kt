package kotlinCourse.testing

interface CustomerDao {
    abstract fun getById(id: Int): Customer?
    abstract fun getAll(): List<Customer>
}