import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.sber.qa.*
import ru.sber.qa.Scanner
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.*
import kotlin.random.Random

internal class HrDepartmentTest {

    val data = Random.nextBytes(127)
    val hrEmployeeNumber = 1922L
    var certificateRequest = mockk<CertificateRequest>()

    @Test
    fun odd() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-07T17:01:42.00Z"), ZoneId.systemDefault())
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertThrows(NotAllowReceiveRequestException::class.java, { HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun even() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-06T17:01:42.00Z"), ZoneId.systemDefault())
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertThrows(NotAllowReceiveRequestException::class.java, { HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun weekend() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-11T17:01:42.00Z"), ZoneId.systemDefault())
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertThrows(WeekendDayException::class.java, { HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun processWorkCorrectly() {
        val certificateRequest = CertificateRequest(100L, CertificateType.NDFL)

        mockkObject(Scanner)
        every { Scanner.getScanData() } returns data

        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(certificateRequest, certificate.certificateRequest)
        assertEquals(data, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)

        unmockkAll()
    }
}