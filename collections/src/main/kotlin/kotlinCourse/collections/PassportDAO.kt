package kotlinCourse.collections

import java.time.LocalDate

class PassportDAO {
    private val data: List<Passport> = listOf(
        Passport(1, "3823-748292", LocalDate.of(1980, 5, 4),
            "Anakin", "Skywalker"),
        Passport(2, "4816-516513", LocalDate.of(2002, 5, 25),
            "Luke", "Skywalker"),
        Passport(3, "9372-649674", LocalDate.of(1822, 8, 17),
            "Chewbacca", "Chewie"),
        Passport(4, "5392-868416", LocalDate.of(1939, 12, 20),
            "Sheev", "Palpatine"),
        Passport(5, "7491-562957", LocalDate.of(1964, 1, 31),
            "Obi-Wan", "Kenobi"),
    )

    fun getAll(): List<Passport> = data

    fun getByOwnerId(id: Int) = data.find { it.ownerId == id }
}