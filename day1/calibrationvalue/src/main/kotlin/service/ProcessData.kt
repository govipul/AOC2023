package service

import model.NumberPos
import service.CalibrateValue

class ProcessData {

    private val cal: CalibrateValue = CalibrateValue()

    private fun readInput(fileName: String): List<String>? {
        return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
    }

    fun process(): List<Int> {
        val inputData = this.readInput("input.txt")
        val result = mutableListOf<Int>()
        inputData?.stream()?.forEach {
            val data = mutableListOf<NumberPos>()
            data.addAll(cal.getNumber(it))
            data.addAll(cal.getStringToDigit(it))
            val sortedList = data.sortedBy { it.pos }
            result.add(sortedList[0].number*10+sortedList[data.lastIndex].number)
        }
        return result
    }
}