package kotlinCourse.http.services

import kotlinCourse.http.model.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Int> {
}