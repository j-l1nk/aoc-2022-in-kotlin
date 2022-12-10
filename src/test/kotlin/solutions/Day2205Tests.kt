package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2205Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2205"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2205"
    }

   @Test
    fun dayTests() {
       val day = Day2205(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals("CMZ", day.solveDayPartA())
       assertEquals("MCD", day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2205(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals("TPGVQPFDH", day.solveDayPartA())
        assertEquals("DMRDFRHHH", day.solveDayPartB())
    }
}