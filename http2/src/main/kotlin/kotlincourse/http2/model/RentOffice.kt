package kotlinCourse.http2.model

import javax.persistence.*

@Entity
@Table(name = "rent_office", schema = "my_schema")
class RentOffice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val title: String,
    val address: String
)