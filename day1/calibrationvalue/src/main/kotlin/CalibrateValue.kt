import model.NumberPos
import java.awt.font.NumericShaper

class CalibrateValue {

    private val stringToDigit = mapOf<String, Int>(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun getNumber(input: String?): List<NumberPos> {
        val result = mutableListOf<NumberPos>()
        if (!input.isNullOrBlank()) {
            val result2 = input.filter { it.isDigit() }
            input.mapIndexed{index, char -> if(char.isDigit()) result.add(NumberPos(char.digitToInt(), index))}
        }
        return result
    }

    fun getStringToDigit(input: String): List<NumberPos> {
        val result  = mutableListOf<NumberPos>()
        var startIndex: Int = 0
        while (startIndex < input.length-1) {
            val data = input.findAnyOf(stringToDigit.keys, startIndex = startIndex, ignoreCase = true)
            if (data != null) {
                val number = stringToDigit[data.second]
                number?.let { NumberPos(number = it, pos = data.first) }?.let { result.add(it) }
                startIndex = data.first-1 + data.second.length-1
            } else {
                break
            }
        }
        return result
    }
}
