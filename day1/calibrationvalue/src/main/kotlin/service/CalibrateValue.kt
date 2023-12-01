package service

import model.NumberPos

class CalibrateValue {

    private val stringToDigit = mapOf(
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
            input.mapIndexed{index, char -> if(char.isDigit()) result.add(NumberPos(char.digitToInt(), index))}
        }
        return result
    }

    fun getStringToDigit(input: String): List<NumberPos> {
        val result  = mutableListOf<NumberPos>()
        var searchIndex = 0
        while (searchIndex < input.length-1) {
            val data = input.findAnyOf(stringToDigit.keys, startIndex = searchIndex, ignoreCase = true)
            if (data != null) {
                val number = stringToDigit[data.second]
                number?.let { NumberPos(number = it, pos = data.first) }?.let { result.add(it) }
                searchIndex = data.first-1 + data.second.length-1
            } else {
                break
            }
        }
        return result
    }
}
