package day01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import readTest

class Day01Test {
    private val day = Day01
    private val input = readTest(day.nb)

    @Test
    fun testPart1() {
        assertEquals(11, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(31, day.part2(input))
    }
 }
