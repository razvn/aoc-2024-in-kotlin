package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day05Test {
    private val day = Day05
    private val input = readTest(day.nb)


    @Test
    fun testDecodeData() {
        val (rules, pages) = day.decodeData(input)

        assertEquals(21, rules.size)
        assertEquals(6, pages.size)
        assertEquals(Day05.OrderRule(53,13), rules.last())
        assertEquals("[97, 13, 75, 29, 47]", pages.last().toString())
    }

    @Test
    fun testPart1() {
        assertEquals(143, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(123, day.part2(input))
    }
}
