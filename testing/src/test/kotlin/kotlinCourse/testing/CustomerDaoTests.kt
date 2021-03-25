package kotlinCourse.testing

import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
class CustomerDaoTests {
    private val slotId = slot<Int>()
    private val testCustomer = Customer("Ivan", 1990)
    private val mock = mockk<CustomerDao> {
        every { getById(capture(slotId)) } answers {
            if (slotId.captured < 5) testCustomer else null
        }
        every { getAll() } returns listOf(testCustomer)
    }

    @ParameterizedTest
    @ValueSource(ints = [-100, -1, 0, 1, 2, 3, 4])
    fun `getById for less than 5 returns customer`(id: Int) {
        // Act
        val result = mock.getById(id)

        // Assert
        assertEquals(testCustomer, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 6, 10, 100, 23421])
    fun `getById for more or equal 5 returns null`(id: Int) {
        // Act
        val result = mock.getById(id)

        // Assert
        assertNull(result)
    }

    @Test
    fun `getById called zero times`() {
        // Act
        val result = mock.getAll()

        // Assert
        verify(exactly = 0) { mock.getById(any())}
    }
}