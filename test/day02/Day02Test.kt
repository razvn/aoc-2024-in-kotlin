package day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day02Test {
 private val day = Day02
 private val input = readTest(day.nb)


 @Test
 fun testDecodeData() {
  val expectedLine1 = listOf(7, 6, 4, 2, 1)
  val expectedLine2 = listOf(1, 2, 7, 8, 9)
  val decode = day.decodeData(input)
  assertEquals(expectedLine1, decode.first())
  assertEquals(expectedLine2, decode[1])
  assertEquals(6, decode.size)
 }

 @Test
 fun testPart1() {
  assertEquals(2, day.part1(input))
 }

 @Test
 fun testPart2() {
  assertEquals(4, day.part2(input))
 }
}
