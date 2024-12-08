package day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day08Test {
    private val day = Day08
    private val input = readTest(day.nb)


    @Test
    fun testDecodeData() {
        val decode = day.decodeData(input)
        assertEquals(2, decode.data.size)
    }

    @Test
    fun testPart1() {
        assertEquals(14, day.part1(input))
    }

    @Test
    fun testPart2() {
        assertEquals(34, day.part2(input))
    }

    @Test
    fun debugPart1() {
        val localInput = """
            ............
            ........0...
            .....0......
            ............
            ............
            ............
            ..A.........
            ....A.......
            ............
            ............
        """.trimIndent()

        val s = day.part1(localInput.lines())

        assertEquals(4, s)
    }

    @Test
    fun debugPart2() {
        val localInput = """
            T.........
            ...T......
            .T........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
        """.trimIndent()

        val s = day.part2(localInput.lines())

        assertEquals(9, s)
    }
}


