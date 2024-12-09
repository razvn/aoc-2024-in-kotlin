package day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day09Test {
    private val day = Day09
    private val input = readTest(day.nb)


    @Test
    fun testPart1() {
        assertEquals(1928L, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(2858, day.part2(input))
    }
}

