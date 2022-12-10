package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day2210Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2210"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2210"
    }

   @Test
    fun dayTests() {
       val day = Day2210(FileReader.readStringsFromFile(TEST_FILE))
       val expectedResultForPartB = listOf(
           "##..##..##..##..##..##..##..##..##..##..",
           "###...###...###...###...###...###...###.",
           "####....####....####....####....####....",
           "#####.....#####.....#####.....#####.....",
           "######......######......######......####",
           "#######.......#######.......#######....."
       )

       assertEquals(13140, day.solveDayPartA())
       assertEquals(expectedResultForPartB, day.solveDayPartB())
   }

    @Test
    fun testFinalSolution() {
        val day = Day2210(FileReader.readStringsFromFile(INPUT_FILE))
        val expectedResultForPartB = listOf(
            "####.####.###..###..###..####.####.####.",
            "#.......#.#..#.#..#.#..#.#.......#.#...."
        )
        assertEquals(14720, day.solveDayPartA())
        assertEquals(expectedResultForPartB[0], day.solveDayPartB()[0])
        assertEquals(expectedResultForPartB[1], day.solveDayPartB()[1])
    }
}