package kotlincourse.http.services

import kotlinCourse.http.model.Car
import org.springframework.stereotype.Service

@Service
class TestCarDao : CarDao {
    override fun getAll(): List<Car> = listOf(
        Car(1, "3MZBM1U73FM126396", "Hyundai Solaris", 2018, 1),
        Car(2, "5NMSH13E58H181509", "KIA Rio", 2019, 1),
        Car(3, "2G1FZ1EE9F9797155", "BMW X6", 2020, 2),
        Car(4, "KL1TD66698B103831", "KIA K5", 2021, 2),
        Car(5, "1LNHM97V62Y676546", "VW Passat", 2018, 3)
    )

    override fun getById(id: Int): Car? =
        getAll().firstOrNull {
            it.id == id
        }
}