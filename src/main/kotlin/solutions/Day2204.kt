package solutions

class Day2204(private val inputStrings: List<String>) {
    fun solveDayPartA() = inputStrings
        .map{ it.toIDRanges() }
        .count{ it.first.contains(it.second) || it.second.contains(it.first) }

    fun solveDayPartB() = inputStrings
        .map{ it.toIDRanges() }
        .count{ it.first.overlapsWith(it.second) || it.second.overlapsWith(it.first) }

    private fun Pair<Int, Int>.contains(other: Pair<Int, Int>) = this.first <= other.first && this.second >= other.second

    private fun String.toIDRanges() = this
        .split(",", "-")
        .let { Pair(Pair(it[0].toInt(), it[1].toInt()), Pair(it[2].toInt(), it[3].toInt())) }

    private fun Pair<Int, Int>.overlapsWith(other: Pair<Int, Int>) = this.first.overlapsWith(other) || this.second.overlapsWith(other)

    private fun Int.overlapsWith(pair: Pair<Int, Int>) = this >= pair.first && this <= pair.second
}