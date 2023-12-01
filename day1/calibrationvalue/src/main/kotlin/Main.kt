import service.ProcessData

fun main() {
    val process = ProcessData()
    val result = process.process()
    println("Sum of number is ${result.sum()}")
}
