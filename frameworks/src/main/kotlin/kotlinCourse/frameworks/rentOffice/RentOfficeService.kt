package kotlinCourse.frameworks.rentOffice

class RentOfficeService(private val dao: RentOfficeDao) {
    fun findAll(): List<RentOffice> = dao.findAll()

    fun findById(id: Int): RentOffice? = dao.findById(id)

    fun create(title: String, address: String): RentOffice =
        dao.create(title, address)

    fun update(rentOffice: RentOffice): Int = dao.update(rentOffice)

    fun delete(id: Int): Int = dao.delete(id)
}
