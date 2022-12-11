package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day2211Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2211"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2211"
    }

   @Test
    fun dayTests() {
       val day = Day2211(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(10605, day.solveDayPartA())
       assertEquals(2713310158, day.solveDayPartB())
   }

    @Test
    fun testFinalSolution() {
        val day = Day2211(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(58794, day.solveDayPartA())
        assertEquals(20151213744, day.solveDayPartB())
    }
}