package solutions

import kotlin.math.absoluteValue

class Day2209(private val inputStrings: List<String>) {
    fun solveDayPartA(): Int {
        val head = Coordinate(0,0)
        val tail = Coordinate(0,0)
        val positionsVisitedAtLeastOnce = mutableSetOf<Coordinate>()
        val instructions = inputStrings.map { line -> line.split(" ").let { Pair(it[0].toDirection(), it[1].toInt()) } }

        instructions
            .forEach { instruction ->
                (1..instruction.second).forEach { _ ->
                    head.move(instruction.first)
                    tail.follow(head)
                    positionsVisitedAtLeastOnce.add(tail.copy())
                }
            }

        return positionsVisitedAtLeastOnce.size
    }

    fun solveDayPartB(): Int {
        val rope = List(10) { Coordinate(0,0) }
        val positionsVisitedAtLeastOnce = mutableSetOf(rope.last().copy())
        val instructions = inputStrings.map { line -> line.split(" ").let { Pair(it[0].toDirection(), it[1].toInt()) } }

        instructions
            .forEach { instruction ->
                (1..instruction.second).forEach {
                    rope.first().move(instruction.first)

                    for (i in (1 until rope.size)) {
                        rope[i].follow(rope[i-1])
                    }

                    positionsVisitedAtLeastOnce.add(rope.last().copy())
                }
            }

        return positionsVisitedAtLeastOnce.size
    }
}

data class Coordinate(
    var x: Int,
    var y: Int
) {
    fun move(direction: Direction) = when (direction) {
            Direction.UP    -> y += 1
            Direction.DOWN  -> y -= 1
            Direction.RIGHT -> x += 1
            Direction.LEFT  -> x -= 1
        }

    fun follow(head: Coordinate) {
        if (this.isNextTo(head)) {
            return
        }

        if (head.y-this.y == 2 && (head.x-this.x).absoluteValue == 1) {
            // Head is above Tail
            this.x = head.x
            this.y = head.y-1
            return
        } else if (head.y-this.y == -2 && (head.x-this.x).absoluteValue == 1) {
            // Head is under Tail
            this.x = head.x
            this.y = head.y+1
            return
        } else if (head.x-this.x == 2 && (head.y-this.y).absoluteValue == 1) {
            // Head is right of Tail
            this.x = head.x-1
            this.y = head.y
            return
        } else if (head.x-this.x == -2 && (head.y-this.y).absoluteValue == 1) {
            // Head is left of Tail
            this.x = head.x+1
            this.y = head.y
            return
        }

        // Move x
        when {
            head.x > this.x+1 -> this.x++
            head.x < this.x-1 -> this.x--
        }

        // Move y
        when {
            head.y > this.y+1 -> this.y++
            head.y < this.y-1 -> this.y--
        }
    }

    private fun getCorners() = listOf(
        Coordinate(this.x-1, this.y+1),
        Coordinate(this.x+1, this.y+1),
        Coordinate(this.x-1, this.y-1),
        Coordinate(this.x+1, this.y-1)
    )

    private fun isNextTo(coordinate: Coordinate) = this.x in (coordinate.x-1..coordinate.x+1) && this.y in (coordinate.y-1..coordinate.y+1)
}

fun String.toDirection() = when(this) {
    "U" -> Direction.UP
    "D" -> Direction.DOWN
    "R" -> Direction.RIGHT
    "L" -> Direction.LEFT
    else -> throw Exception("Unknown Direction")
}

enum class Direction{
    UP,
    DOWN,
    RIGHT,
    LEFT
}