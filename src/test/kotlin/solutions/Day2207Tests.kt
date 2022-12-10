package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day2207Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2207"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2207"
    }

   @Test
    fun dayTests() {
       val day = Day2207(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(95437L, day.solveDayPartA())
       // assertEquals("MCD", day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2207(FileReader.readStringsFromFile(INPUT_FILE))
        assertNotEquals(1108687L, day.solveDayPartA())
        assertNotEquals(1205068L, day.solveDayPartA())
        assertNotEquals(1310866L, day.solveDayPartA())
        assertEquals(95437L, day.solveDayPartA())
        // assertEquals("DMRDFRHHH", day.solveDayPartB())
    }
}