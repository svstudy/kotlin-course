package kotlinCourse.http.model

import javax.persistence.*

@Entity
@Table(name = "car")
class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val vin: String,
    val model: String,
    val year: Int,
    @Column("rent_office_id")
    val rentOfficeId: Int
)
