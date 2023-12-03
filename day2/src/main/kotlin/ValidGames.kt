import model.ElfGame

class ValidGames {

    private val MAX_RED: Int = 12
    private val MAX_GREEN: Int = 13
    private val MAX_BLUE: Int = 14

    private fun readInput(fileName: String): List<String>? {
        return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
    }

    fun validaGame(input: ElfGame): Boolean {
        return input.blue <= MAX_BLUE && input.red <= MAX_RED && input.green <= MAX_GREEN
    }

    fun process(): List<Int> {
        val inputData = this.readInput("input.txt")
        val validGameData = mutableListOf<Int>()
        inputData?.forEach {
            val data = it.split(":")
            val gameNumber:Int = data[0].filter { it.isDigit() }.toInt()
            val gameData = data[1].split(";")
            var isValidGame:Boolean = true
            val elfGame: ElfGame = ElfGame()
            for (game in gameData) {
                val pairs = mutableMapOf<String, Int>()
                 game.split(", ").forEach {
                    val (quantity, color) = it.trim().split(" ")
                    pairs[color] = quantity.toInt()
                }
                if (elfGame.red < pairs.getOrDefault("red", 0)) elfGame.red = pairs.getOrDefault("red", 0)
                if (elfGame.green < pairs.getOrDefault("green", 0)) elfGame.green = pairs.getOrDefault("green", 0)
                if (elfGame.blue < pairs.getOrDefault("blue", 0)) elfGame.blue = pairs.getOrDefault("blue", 0)
            }
            validGameData.add(elfGame.red * elfGame.green * elfGame.blue)
        }
        return validGameData
    }
}