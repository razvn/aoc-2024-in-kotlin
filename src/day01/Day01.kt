package day01

import checkTest
import readInput
import readTest
import runPart
import kotlin.math.abs

fun main() {
    val day = "Day01"
    val test = false

    if (test) {
        //Test input
        val testInput = readTest(day)
        checkTest(1, ::part1, testInput, 11)
        checkTest(2, ::part2, testInput, 31)
    } else {
        val input = readInput(day)
        runPart(1, input, ::part1)  // Part 1: 1722302
        runPart(2, input, ::part2)  // Part 2: 20373490
    }
}

private fun decodeData(input: List<String>): Pair<List<Int>, List<Int>> {
    val (firstList, secondList) = input.map { it.split("   ") }
        .map { it.first().toInt() to it.last().toInt() }
        .unzip()

    return firstList.sorted() to secondList.sorted()
}

private fun part1(input: List<String>): Int {
    val (firstList, secondList) = decodeData(input)

    return firstList.zip(secondList) { a, b -> abs(a - b) }.sum()
}

private fun part2(input: List<String>): Int {
    val (firstList, secondList) = decodeData(input)
    val secondListCount = secondList.groupingBy { it }.eachCount()

    return firstList.sumOf { it * secondListCount.getOrDefault(it, 0) }
}
