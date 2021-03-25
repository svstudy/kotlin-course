package kotlinCourse.testing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SimpleMathTests {
    private val sut = SimpleMath()

    @ParameterizedTest
    @ValueSource(ints = [-100, -4, -2, 0, 2, 4, 100])
    fun `isEven for even should return true`(n: Int) {
        // Act
        val result = sut.isEven(n)

        // Assert
        assertEquals(true, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [-101, -5, -1, 1, 3, 99])
    fun `isEven for odd should return false`(n: Int) {
        // Act
        val result = sut.isEven(n)

        // Assert
        assertEquals(false, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1981643, -100, -5, -2, -1])
    fun `sig for negative should return -1`(n: Int) {
        // Act
        val result = sut.sig(n)

        // Assert
        assertEquals(-1, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 20, 99, 6468681])
    fun `sig for positive should return 1`(n: Int) {
        // Act
        val result = sut.sig(n)

        // Assert
        assertEquals(1, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [0])
    fun `sig for 0 return 0`(n: Int) {
        // Act
        val result = sut.sig(n)

        // Assert
        assertEquals(0, result)
    }
}