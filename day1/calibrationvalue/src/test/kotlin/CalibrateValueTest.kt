import model.NumberPos
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.awt.font.NumericShaper

class CalibrateValueTest {
    private val calibrateValue: CalibrateValue = CalibrateValue()

    @Test
    @DisplayName("Test with 0 digit in input")
    fun testGetNumberNoDigit() {
        val result = calibrateValue.getNumber("abc")
        assertTrue(result.isEmpty())
    }

    @Test
    @DisplayName("Test with 0 digit in input")
    fun testGetNumberNoDigitNullInput() {
        val result = calibrateValue.getNumber(null)
        assertTrue(result.isEmpty())
    }

    @Test
    @DisplayName("Test with 2 digit in input")
    fun testGetNumberDigit() {
        val expected = listOf(NumberPos(1, 0), NumberPos(2, 4))
        val result = calibrateValue.getNumber("1abc2")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with 2 digit in input")
    fun testGetNumberMellomDigit() {
        val expected = listOf(
            NumberPos(3, 3),
            NumberPos(8, 7)
        )
        val result = calibrateValue.getNumber("pqr3stu8vwx")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with N digit in input")
    fun testGetNumberMultipleDigit() {
        val expected = listOf(
            NumberPos(1, 1),
            NumberPos(2, 3),
            NumberPos(3, 5),
            NumberPos(4, 7),
            NumberPos(5, 9)
        )
        val result = calibrateValue.getNumber("a1b2c3d4e5f")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with 1 digit in input")
    fun testGetNumberSingleDigit() {
        val expected = listOf(NumberPos(7, 4))
        val result = calibrateValue.getNumber("treb7uchet")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with One as input")
    fun testStringNumberToDigit() {
        val expected = listOf(NumberPos(1, 0))
        val result = calibrateValue.getStringToDigit("one")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with String Number as input")
    fun testStringNumbersToDigit() {
        val expected = listOf(
            NumberPos(8, 0),
            NumberPos(2, 4),
            NumberPos(3, 7)
        )
        val result = calibrateValue.getStringToDigit("eightwothree")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with String Number as input")
    fun testStringNoNumberToDigit() {
        val result = calibrateValue.getStringToDigit("treb7uchet")
        assertTrue(result.isEmpty())
    }

    @Test
    @DisplayName("Test with String Number as input")
    fun testStringRandomNumberToDigit() {
        val expected = listOf(NumberPos(3, 0))
        val result = calibrateValue.getStringToDigit("three656")
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test with String Number as input")
    fun testStringRandomNumber2ToDigit() {
        val expected = listOf(NumberPos(6, 6))
        val result = calibrateValue.getStringToDigit("7pqrstsixteen")
        assertEquals(expected, result)
    }

    @Test
    fun testStringSpecialNumber2ToDigit() {
        val expected = listOf(NumberPos(8, 0), NumberPos(3, 4))
        val result = calibrateValue.getStringToDigit("eighthree")
        assertEquals(expected, result)
    }

    @Test
    fun testString2FullString() {
        val expected = listOf(NumberPos(3, 1), NumberPos(8, 6), NumberPos(5, 19))
        val result = calibrateValue.getStringToDigit("6threeeightjzcgsnclfive7txvgsdxnt")
        assertEquals(expected, result)
    }

    @Test
    fun testString3FullString() {
        val expected = listOf(NumberPos(2, 1),
            NumberPos(1, 3),
            NumberPos(4,7))
        val result = calibrateValue.getStringToDigit("xtwone3four")
        assertEquals(expected, result)
    }


}