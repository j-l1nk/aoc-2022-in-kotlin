package solutions

import org.junit.Test
import readers.FileReader
import kotlin.test.assertEquals

class Day2206Tests {
    companion object {
        private const val INPUT_FILE = "src/main/resources/Inputs/Day2206"
    }

    @Test
    fun dayPartATests() {
        assertEquals(5, Day2206("bvwbjplbgvbhsrlpgdmjqwftvncz").solveDayPartA())
        assertEquals(6, Day2206("nppdvjthqldpwncqszvftbrmjlhg").solveDayPartA())
        assertEquals(10, Day2206("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").solveDayPartA())
        assertEquals(11, Day2206("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").solveDayPartA())
    }

    @Test
    fun dayPartBTests() {
        assertEquals(19, Day2206("mjqjpqmgbljsphdztnvjfqwrcgsmlb").solveDayPartB())
        assertEquals(23, Day2206("bvwbjplbgvbhsrlpgdmjqwftvncz").solveDayPartB())
        assertEquals(23, Day2206("nppdvjthqldpwncqszvftbrmjlhg").solveDayPartB())
        assertEquals(29, Day2206("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").solveDayPartB())
        assertEquals(26, Day2206("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").solveDayPartB())
    }

    @Test
    fun testFinalSolution() {
        val day = Day2206(FileReader.readStringsFromFile(INPUT_FILE).first())
        assertEquals(1100, day.solveDayPartA())
        assertEquals(2421, day.solveDayPartB())
    }
}