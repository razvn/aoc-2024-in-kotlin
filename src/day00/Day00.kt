package day00

import readInput
import readTest
import kotlin.math.abs

private fun decodeData(input: List<String>): List<String> {
    return input
}

private fun part1(input: List<String>): Int {
    val data = decodeData(input)

    return data.size
}

private fun part2(input: List<String>): Int {
    val data = decodeData(input)

    return data.size
}

fun main() {
    val day = "Day00"
    // test if implementation meets criteria from the description, like:
    val testInput = readTest(day)
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput(day)
    // Part 1:
    // Part 2:
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
