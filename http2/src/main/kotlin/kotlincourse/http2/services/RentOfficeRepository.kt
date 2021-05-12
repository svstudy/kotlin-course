package kotlinCourse.http2.services

import kotlinCourse.http2.model.RentOffice
import org.springframework.data.jpa.repository.JpaRepository

interface RentOfficeRepository : JpaRepository<RentOffice, Int> {
}