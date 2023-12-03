
private val game: ValidGames = ValidGames()

fun main(args: Array<String>) {
   val result = game.process()
    println(result.sum())
}