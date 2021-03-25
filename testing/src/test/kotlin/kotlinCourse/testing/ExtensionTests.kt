package kotlinCourse.testing

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import  org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class ExtensionTests {

    @ParameterizedTest
    @ValueSource(ints = [-99, -4, -2, 0, 1, 5, 100])
    fun `divideBy zero should throw ArithmeticException`(numerator: Int) {
        val exception = assertThrows(ArithmeticException::class.java) {
            // Act
            numerator.divideBy(0)
        }
        // Assert
        assertEquals("/ by zero", exception.message)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 10",
        "5, 5",
        "20, 7",
        "1000, 50",
        "0, 93412"
    )
    fun `divideBy non-zero returns correct fraction`(numerator: Int, denominator: Int) {
        // Assign
        val expected = numerator / denominator
        // Act
        val result = numerator.divideBy(denominator)
        // Assert
        assertEquals(expected, result)
    }
}