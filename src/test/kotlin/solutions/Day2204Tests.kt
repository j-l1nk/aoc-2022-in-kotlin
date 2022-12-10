package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2204Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2204"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2204"
    }

   @Test
    fun dayTests() {
       val day = Day2204(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(2, day.solveDayPartA())
       assertEquals(4, day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2204(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(464, day.solveDayPartA())
        assertEquals(770, day.solveDayPartB())
    }
}