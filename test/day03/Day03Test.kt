package day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTest

class Day03Test {
    private val day = Day03
    private val input = readTest(day.nb)
    private val part1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    private val part2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"


    @Test
    fun testDecodeData() {
        val decode = day.decodeData(input)
        assertEquals(part1, decode)
    }

    @Test
    fun testDecodeData2() {
        val input2 = readInput(day.nb, "_test2")
        val decode = day.decodeData(input2)
        assertEquals(part2, decode)
    }

    @Test
    fun testPart1() {
        assertEquals(161, day.part1(input))
    }

    @Test
    fun testPart2() {
        val input2 = readInput(day.nb, "_test2")
        assertEquals(48, day.part2(input2))
    }

    @Test
    fun testPart2_input() {
        val input2 = readInput(day.nb)
        assertEquals(53783319, day.part2(input2))
    }
}
