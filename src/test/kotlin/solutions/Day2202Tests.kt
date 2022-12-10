package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day2202Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2202"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2202"
    }

    @Test
    fun dayTests() {
        val day = Day2202(FileReader.readStringsFromFile(TEST_FILE))
        assertEquals(15, day.solveDayPartA())
        assertEquals(12, day.solveDayPartB())
    }

    @Test
    fun partATestsWithManualData() {
        assertEquals(1 + 3, Day2202(listOf("A X")).solveDayPartA())
        assertEquals(2 + 6, Day2202(listOf("A Y")).solveDayPartA())
        assertEquals(3 + 0, Day2202(listOf("A Z")).solveDayPartA())
    }

    @Test
    fun partBTestsWithManualData() {
        assertEquals(1 + 3, Day2202(listOf("A Y")).solveDayPartB())
        assertEquals(1 + 0, Day2202(listOf("B X")).solveDayPartB())
        assertEquals(1 + 6, Day2202(listOf("C Z")).solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2202(FileReader.readStringsFromFile(INPUT_FILE))
        assertNotEquals(13848, day.solveDayPartA())
        assertNotEquals(10604, day.solveDayPartA())
        assertEquals(13682, day.solveDayPartA())
        assertEquals(12881, day.solveDayPartB())
    }
}