package solutions

class Day2203(private val inputStrings: List<String>) {
    fun solveDayPartA() = inputStrings
        .map { it.chunked(it.length/2) }
        .flatMap { it[0] intersect it[1] }
        .sumOf { it.toPoints() }

    fun solveDayPartB() = inputStrings
        .chunked(3)
        .flatMap { it[0] intersect it[1] intersect it[2].toSet() }
        .sumOf { it.toPoints() }

    private fun Char.toPoints() = when(this.isLowerCase()) {
        true -> this.code - 96
        else -> this.code - 38
    }

    private infix fun String.intersect(other: String) = this.toSet() intersect other.toSet()
}