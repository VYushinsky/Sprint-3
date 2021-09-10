import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.*
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random
import kotlin.test.assertEquals

class ScannerTest {

    @BeforeEach
    fun before(){
        mockkObject(Random)
    }

    @Test
    fun getScanData(){
        val expected = Random.nextBytes(100)
        every {Random.nextLong(5000L, 15000L)} returns 7654L

        assertEquals(expected.size, Scanner.getScanData().size)
        assertDoesNotThrow { Scanner.getScanData() }
    }


    @Test
    fun exc(){
        every {Random.nextLong(5000L, 15000L)} returns 10001L
        assertThrows<ScanTimeoutException> { Scanner.getScanData() }

    }

    @AfterEach
    fun unMockk() {
        unmockkAll()
    }
}
