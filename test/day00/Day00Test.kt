package day00

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day00Test {
 private val day = Day00
 private val input = readTest(day.nb)


 @Test
 fun testDecodeData() {
  val decode = day.decodeData(input)
  assertEquals(1, decode.size)
 }

 @Test
 fun testPart1() {
  assertEquals(0, day.part1(input))
 }

 @Test
 fun testPart2() {
  assertEquals(0, day.part2(input))
 }
}
