package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day2208Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2208"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2208"
    }

   @Test
    fun dayTests() {
       val day = Day2208(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(21, day.solveDayPartA())
       assertEquals(8, day.solveDayPartB())
    }

    @Test
    fun `setVisible() should return correct values`() {
        assertEquals(listOf(), listOf<Tree>().setVisible())
        assertEquals(listOf(Tree(3, true, 3), Tree(0, false, 3)), listOf(Tree(3), Tree(0)).setVisible())
        assertEquals(listOf(Tree(3, true, 3), Tree(0, false, 3), Tree(3, false,3)), listOf(Tree(3), Tree(0), Tree(3)).setVisible())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2208(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(1776, day.solveDayPartA())
        assertNotEquals(345600, day.solveDayPartB())
        assertEquals(234416, day.solveDayPartB())
    }
}