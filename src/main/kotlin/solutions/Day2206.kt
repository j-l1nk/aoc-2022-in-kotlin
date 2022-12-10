package solutions

class Day2206(private val inputString: String) {
    companion object {
        const val WINDOW_SIZE_PART_A = 4
        const val WINDOW_SIZE_PART_B = 14
    }

    fun solveDayPartA() = inputString.processedCharactersUntilPacketStart(windowSize= WINDOW_SIZE_PART_A)

    fun solveDayPartB() = inputString.processedCharactersUntilPacketStart(windowSize= WINDOW_SIZE_PART_B)

    private fun String.processedCharactersUntilPacketStart(windowSize: Int) = this
        .windowed(windowSize)
        .indexOfFirst { it.length == it.toSet().size }
        .let { it + windowSize }
}