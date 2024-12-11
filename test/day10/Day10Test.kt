package day10

import day10.Day10.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day10Test {
    private val day = Day10
    private val input = readTest(day.nb)


    @Test
    fun testDecodeData() {
        val decode = day.decodeData(input)
        assertEquals(10, decode.size)
    }

    @Test
    fun testPart1() {
        assertEquals(36, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(81, day.part2(input))
    }


    @Test
    fun x() {
        val data = """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
        """.trimIndent()

        val res = day.part1(data.lines())
        assertEquals(2, res)
    }

    @Test
    fun y() {
        val data = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent()

        val res = day.part1(data.lines())
        assertEquals(4, res)
    }

    @Test
    fun z() {
        val data = """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
        """.trimIndent()

        val res = day.part1(data.lines())
        assertEquals(3, res)
    }

}
