package day00

import checkTest
import readInput
import readTest
import runPart

fun main() {
    val day = "Day00"
    val test = true

    if (test) {
        //Test input
        val testInput = readTest(day)
        checkTest(1, ::part1, testInput, 1)
        checkTest(2, ::part2, testInput, 1)
    } else {
        val input = readInput(day)
        runPart(1, input, ::part1)  // Part 1:
        runPart(2, input, ::part2)  // Part 2:
    }
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
