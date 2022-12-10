package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2209Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2209"
        private const val TEST_FILE = "src/test/resources/Inputs/TestFile2209"
    }

   @Test
    fun dayTestsPartA() {
       val day = Day2209(FileReader.readStringsFromFile(TEST_FILE))
       assertEquals(13, day.solveDayPartA())
    }

    @Test
    fun dayTestsPartB() {
        val day = Day2209(listOf("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20"))
        assertEquals(36, day.solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2209(FileReader.readStringsFromFile(INPUT_FILE))
        assertEquals(5619, day.solveDayPartA())
        assertEquals(2376, day.solveDayPartB())
    }

    @Test
    fun testFollow() {
        val tail = Coordinate(2,4)
        tail.follow(Coordinate(4,3))

        assertEquals(Coordinate(3,3), tail)
    }

}