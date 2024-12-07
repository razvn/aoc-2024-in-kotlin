package day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day07Test {
    private val day = Day07
    private val input = readTest(day.nb)


    @Test
    fun testDecodeData() {
        val decode = day.decodeData(input)
        assertEquals(9, decode.size)
    }

    @Test
    fun testPart1() {
        assertEquals(3749, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(11387, day.part2(input))
    }
}
