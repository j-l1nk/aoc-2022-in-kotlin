package solutions

import java.lang.Exception

class Day2211(private val inputStrings: List<String>) {
    companion object{
        const val SEPARATOR = "/"
    }
    fun solveDayPartA() = solveDay(rounds=20, divideBy=3)

    fun solveDayPartB() = solveDay(rounds=10000, divideBy=1)

    private fun solveDay(rounds: Int, divideBy: Int): Long {
        val monkeys = inputStrings.toMonkeys(divideBy=divideBy)
        val moduloBy = monkeys.fold(1L) { actualModulo, monkey -> actualModulo * monkey.testValue }

        for(i in 1..rounds) {
            monkeys
                .forEach {
                    it.introspectItems(moduloBy=moduloBy).forEach { thrownItem ->
                        monkeys[thrownItem.first].items += thrownItem.second
                    }

                    it.items = listOf()
                }
        }

        return monkeys.sortedByDescending { it.intropectedTimes }.take(2).let{ it[0].intropectedTimes * it[1].intropectedTimes }
    }
}

private fun List<String>.toMonkeys(divideBy: Int) = this
    .joinToString(Day2211.SEPARATOR)
    .split(Day2211.SEPARATOR + Day2211.SEPARATOR)
    .map { it.split(Day2211.SEPARATOR).toMonkey(divideBy = divideBy) }

private fun List<String>.toMonkey(divideBy: Int) = Monkey(
    items = this[1].substringAfter("Starting items: ").split(", ").map { it.toLong() },
    operation = this[2].substringAfter("Operation: new = ").split(" ").let { Triple(it[0], it[1], it[2]) },
    testValue = this[3].substringAfter("Test: divisible by ").toLong(),
    ifTrue = this[4].substringAfter("If true: throw to monkey ").toInt(),
    ifFalse = this[5].substringAfter("If false: throw to monkey ").toInt(),
    divideBy = divideBy
)

class Monkey(
    var items: List<Long>,
    val operation: Triple<String, String, String>,
    var testValue: Long,
    val ifTrue: Int,
    val ifFalse: Int,
    var intropectedTimes: Long = 0,
    var divideBy: Int = 3
) {
    fun introspectItems(moduloBy: Long) = items
        .also { intropectedTimes += it.size }
        .map {
            val zeroValue: Long = 0
            val (firstValue, thirdValue) = Pair(operation.first.toOperationValue(it), operation.third.toOperationValue(it))
            val newValue: Long = when (operation.second) {
                "+" -> firstValue + thirdValue
                "*" -> firstValue * thirdValue
                else -> throw Exception("Unknown operation: ${operation.second}")
            }.let { worryLevel -> (worryLevel/divideBy) % moduloBy }

            when (newValue % testValue == zeroValue) {
                true -> Pair(ifTrue, newValue)
                else -> Pair(ifFalse, newValue)
        }
    }

    private fun String.toOperationValue(oldValue: Long) = this.toLongOrNull() ?: oldValue
}