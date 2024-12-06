package day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readTest

class Day06Test {
 private val day = Day06
 private val input = readTest(day.nb)


 @Test
 fun testDecodeData() {
  val decode = day.decodeData(input)
  assertEquals(10, decode.size)
 }

 @Test
 fun testPart1() {
  assertEquals(41, day.part1(input))
 }

 @Test
 fun testPart2() {
  assertEquals(6, day.part2(input))
 }
}
