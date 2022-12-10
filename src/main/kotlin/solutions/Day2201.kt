package solutions

class Day2201(private val inputStrings: List<String>) {
    fun solveDayPartA() = inputStrings
        .toCaloriesPerElve()
        .maxOrNull()

    fun solveDayPartB() = inputStrings
        .toCaloriesPerElve()
        .sortedDescending()
        .take(3)
        .sum()

    private fun List<String>.toCaloriesPerElve() = this
        .joinToString(",")
        .split(",,")
        .map { it.split(",").sumOf { number -> number.toLong() } }
}