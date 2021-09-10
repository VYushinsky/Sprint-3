import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import ru.sber.qa.Certificate
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import ru.sber.qa.Scanner
import kotlin.random.Random
import kotlin.test.assertEquals

class CertificateRequestTest {

    val employeeNumber = 12L
    val certificateType = mockk<CertificateType>()
    val certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @Test
    fun numEquals(){
        assertEquals(certificateRequest.employeeNumber, employeeNumber)
    }

    @Test
    fun cerEquals(){
        assertEquals(certificateRequest.certificateType, certificateType)
    }

    @Test
    fun process(){
        val data = Random.nextBytes(100)
        val hrEmployeeNumber = 14L

        mockkObject(Scanner)
        every { Scanner.getScanData() } returns data

        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(data, certificate.data)
        assertEquals(certificateRequest, certificate.certificateRequest)

        unmockkAll()
    }
}