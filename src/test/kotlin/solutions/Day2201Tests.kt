package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2201Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2201"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2201"
    }

   @Test
    fun dayTests() {
       val day = Day2201(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(24000L, day.solveDayPartA())
       assertEquals(45000L, day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2201(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(71124L, day.solveDayPartA())
        assertEquals(204639L, day.solveDayPartB())
    }
}