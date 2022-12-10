package solutions

import java.lang.Exception

class Day2202(private val inputStrings: List<String>) {
    fun solveDayPartA() = inputStrings
        .map { Pair(it[0].toHandShape(), it[2].toHandShape()) }
        .sumOf { it.second.points + it.toGameResult() }

    fun solveDayPartB() = inputStrings
        .map { Pair(it[0].toHandShape(), it[2].toExpectedGameResult()) }
        .sumOf { it.ownHandShape().points + it.second.points }

    private fun Char.toHandShape() = when(this) {
        'A', 'X' -> Rock()
        'B', 'Y' -> Paper()
        'C', 'Z' -> Scissors()
        else -> throw Exception("Unknown Handshape")
    }

    private fun Char.toExpectedGameResult() = when(this) {
        'X' -> Lose()
        'Y' -> Draw()
        'Z' -> Win()
        else -> throw Exception("Unknown expected GameResult")
    }

    private fun Pair<HandShape,HandShape>.toGameResult() = when {
        this.first > this.second -> Lose().points
        this.first == this.second -> Draw().points
        this.first < this.second -> Win().points
        else -> throw Exception("Unknown operation in Game")
    }

    private fun Pair<HandShape, ExpectedGameResult>.ownHandShape() = when {
        first is Rock && second is Win -> Paper()
        first is Rock && second is Draw -> Rock()
        first is Rock && second is Lose -> Scissors()
        first is Paper && second is Win -> Scissors()
        first is Paper && second is Draw -> Paper()
        first is Paper && second is Lose -> Rock()
        first is Scissors && second is Win -> Rock()
        first is Scissors && second is Draw -> Scissors()
        first is Scissors && second is Lose -> Paper()
        else -> throw Exception("Unknown Operation")
    }

    private sealed class HandShape(open val points: Int = 0) {
        operator fun compareTo(second: HandShape) = when {
            this is Paper && second is Rock -> 1
            this is Rock && second is Scissors -> 1
            this is Scissors && second is Paper -> 1
            this is Paper && second is Scissors -> -1
            this is Scissors && second is Rock -> -1
            this is Rock && second is Paper -> -1
            this is Rock && second is Rock -> 0
            this is Paper && second is Paper -> 0
            this is Scissors && second is Scissors -> 0
            else -> throw Exception("Comparing failed")
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is HandShape) return false

            if (points != other.points) return false

            return true
        }

        override fun hashCode(): Int {
            return points
        }
    }

    private class Rock(override val points: Int = 1) : HandShape()
    private class Paper(override val points: Int = 2) : HandShape()
    private class Scissors(override val points: Int = 3) : HandShape()

    private sealed class ExpectedGameResult(open val points: Int = 0)
    private class Win(override val points: Int = 6) : ExpectedGameResult()
    private class Draw(override val points: Int = 3) : ExpectedGameResult()
    private class Lose(override val points: Int = 0) : ExpectedGameResult()

}