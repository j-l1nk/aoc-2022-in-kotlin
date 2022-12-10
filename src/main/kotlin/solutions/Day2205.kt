package solutions

import java.util.*

class Day2205(private val inputStrings: List<String>) {
    fun solveDayPartA(): String {
        val stacks = inputStrings.getStacks()

        inputStrings
            .getInstructions()
            .forEach {
                val (number, indexFrom, indexTo) = it
                for (i in 0..number) {
                    stacks[indexTo].push(stacks[indexFrom].pop())
                }
            }

        return stacks.map { it.pop() }.joinToString("")
    }

    fun solveDayPartB(): String {
        val stacks = inputStrings.getStacks()

        inputStrings
            .getInstructions()
            .forEach {
                val (number, indexFrom, indexTo) = it
                (0..number)
                    .map { _ -> stacks[indexFrom].pop() }
                    .reversed()
                    .forEach { char -> stacks[indexTo].push(char) }
            }

        return stacks.map { it.pop() }.joinToString("")
    }

    private fun List<String>.getStacks() = this
        .map { it.chunked(4) }
        .takeWhile { it.firstOrNull() != " 1  " }
        .reversed()
        .let {
            val stacks = mutableListOf<Stack<Char>>()
            val size = it.first().size
            it.flatten().forEachIndexed { index, elem ->
                stacks.getOrNull(index%size) ?: stacks.add(Stack())

                elem.getOrNull(1)?.let { char ->
                    if (char != ' '){
                        stacks[index%size].push(char)
                    }
                }

            }
            stacks
        }

    private fun List<String>.getInstructions() = this
        .filter { it.startsWith("move ") }
        .map { it.toInstruction() }

    private fun String.toInstruction() = this
        .split("move ", " from ", " to ")
        .filter { it.isNotBlank() }
        .map { it.toInt()-1 }
}