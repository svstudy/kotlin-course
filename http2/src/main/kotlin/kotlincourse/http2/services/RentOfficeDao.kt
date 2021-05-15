package kotlincourse.http2.services

import kotlinCourse.http2.model.RentOffice

interface RentOfficeDao {
    fun getAll(): List<RentOffice>
    fun getById(id: Int): RentOffice?
}