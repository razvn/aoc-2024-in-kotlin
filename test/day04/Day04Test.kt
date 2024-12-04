package day04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import readTest

class Day04Test {
 private val day = Day04
 private val input = readTest(day.nb)


 @Test
 fun testDecodeData() {
  val decode = day.decodeData(input)
  assertEquals(1, decode.size)
 }

 @Test
 fun testXmasLine() {
   val right  = day.xmasEst(1, "XXMASMAX")
  assertTrue(right)
  val left  = day.xmasWest(7, "XXMASAMX")
  assertTrue(left)
  val none  = day.xmasEst(0, "XXMASMX")
  assertFalse(none)
 }

 @Test
 fun testNE() {
  val input = """
   ....S
   ...A.
   ..M..
   .X...
  """.trimIndent()
  val data = day.decodeData(input.lines())
  val ne = day.xmasNE(3, 1, data[3], data)
    assertTrue(ne)
 }

 @Test
 fun testSW() {
  val input = """
   ....X
   ...M.
   ..A..
   .S...
  """.trimIndent()
  val data = day.decodeData(input.lines())
  val ne = day.xmasSW(0, 4, data)
  assertTrue(ne)
 }

 @Test
 fun testNW() {
  val input = """
   ....S...
   .....A...
   ......M...
   .......X...
  """.trimIndent()
  val data = day.decodeData(input.lines())
  val ne = day.xmasNW(3, 7, data)
  assertTrue(ne)
 }

 @Test
 fun testSE() {
  val input = """
   ....X...
   .....M...
   ......A...
   .......S...
  """.trimIndent()
  val data = day.decodeData(input.lines())
  val ne = day.xmasSE(0, 4, data.first(), data)
  assertTrue(ne)
 }

@Test
 fun test1() {
  val input = """
  XXXXXXXXXXX
  XXXXXXXXXXX
  XXXXXXXXXXX
  XXXXXXXXXXX
  XXXXXXXXXXX
  XXXXXXXXXXX
  """.trimIndent()
  val data = day.decodeData(input.lines())
  val ne = day.part1(data)
  println(ne)
 }

 @Test
 fun testPart1() {
  assertEquals(18, day.part1(input))
 }

 @Test
 fun testPart2() {
  assertEquals(9, day.part2(input))
 }
}
