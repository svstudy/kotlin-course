package kotlincourse.http.services

import kotlinCourse.http.model.Car

interface CarDao {
    fun getAll(): List<Car>
    fun getById(id: Int): Car?
}