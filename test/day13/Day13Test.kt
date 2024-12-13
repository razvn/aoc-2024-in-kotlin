package day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day13Test {
    private val day = Day13
    private val input = readTest(day.nb)


    @Test
    fun testDecodeData() {
        val decode = day.decodeData(input)
        assertEquals(4, decode.size)
    }

    @Test
    fun testPart1() {
        assertEquals(480, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(875318608908, day.part2(input))
    }
}
