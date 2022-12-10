package solutions

import java.lang.Exception

class Day2210(private val inputStrings: List<String>) {
    fun solveDayPartA(): Long {
        val cpu = CPU()

        inputStrings
            .map { it.toInstruction() }
            .forEach { cpu.execute(it) }

        return cpu.result
    }


    fun solveDayPartB(): List<String> {
        val crt = CRT()

        inputStrings
            .map { it.toInstruction() }
            .forEach { crt.execute(it) }

        crt.printBitMap()

        return crt.bitMap
    }

    private fun String.toInstruction() = this
        .split(" ")
        .let { Pair(Instruction.valueOf(it[0].uppercase()), it.getOrNull(1)?.toInt()) }
}

class CPU {
    companion object {
        val RELEVANT_CYCLES = listOf(20, 60, 100, 140, 180, 220)
    }
    var result = 0L
    private var registerX: Long = 1L
    private var cycle = 0

    fun execute(command: Pair<Instruction, Int?>) {
        incrementCycle()

        when (command.first) {
            Instruction.NOOP -> return
            Instruction.ADDX -> {incrementCycle(); registerX += command.second ?: throw Exception("No value found to add")}
        }
    }

    private fun incrementCycle() {
        cycle++

        if (cycle in RELEVANT_CYCLES) {
            result += (cycle * registerX)
        }
    }
}

class CRT {
    val bitMap = MutableList(6) { "" }
    private var registerX: Long = 1L
    private var linePosition: Int = 0
    private var spritePosition: Int = 0
    private var cycle = 0

    fun execute(command: Pair<Instruction, Int?>) {
        incrementCycle(null)

        if (command.first == Instruction.ADDX) {
            incrementCycle(command.second)
        }
    }

    fun printBitMap() {
        bitMap.forEach { line -> line.map{when(it) {
            '#' -> print(it)
            '.' -> print(" ")
        }  };println(); }
    }

    private fun incrementCycle(increment: Int?) {
        cycle++
        bitMap[linePosition] += when (registerX in (spritePosition-1..spritePosition+1)) {
            true -> "#"
            else -> "."
        }

        increment?.let { registerX += increment }

        if (cycle % 40 == 0) {
            linePosition++
            spritePosition = 0
        } else {
            spritePosition++
        }
    }
}

enum class Instruction {
    NOOP,
    ADDX
}