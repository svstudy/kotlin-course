package kotlinCourse.testing

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals

class BotForDslTests {
    @Test
    fun `bot should be smart`() {
        // Assign
        val bot = mockk<BotForDsl>() {
            every { name } returns "Dummy"
            every { rating } returns 1
            every { talk("Ping") } returns "Pong"
        }

        // Assert
        assertAll(
            { assertEquals("Dummy", bot.name) },
            { assertEquals(1, bot.rating) },
            { assertEquals("Pong", bot.talk("Ping")) }
        )
    }

}