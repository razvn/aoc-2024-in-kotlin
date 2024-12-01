package day00

import checkTest
import readInput
import readTest
import runPart

fun main() {
    val day = 0
    println("\n===== AOC 2024 : $day =====\n")

    //Test input
    val testInput = readTest(day)
    checkTest(::part1, testInput, 1)
    checkTest(::part2, testInput, 1)

    val input = readInput(day)
    runPart(::part1, input)  // Part 1:
    runPart(::part2, input)  // Part 2:
}

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
