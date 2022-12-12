package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2212Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2212"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2212"
    }

   @Test
    fun dayTests() {
       val day = Day2212(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(31, day.solveDayPartA())
       assertEquals(29, day.solveDayPartB())
   }

    @Test
    fun testFinalSolution() {
        val day = Day2212(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(440, day.solveDayPartA())
        assertEquals(439, day.solveDayPartB())
    }
}