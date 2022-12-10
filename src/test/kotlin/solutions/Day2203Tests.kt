package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2203Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2203"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2203"
    }

   @Test
    fun dayTests() {
       val day = Day2203(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(157, day.solveDayPartA())
       assertEquals(70, day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2203(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(8493, day.solveDayPartA())
        assertEquals(2552, day.solveDayPartB())
    }
}