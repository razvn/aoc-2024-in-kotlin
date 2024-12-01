package day01

import checkTest
import readInput
import readTest
import runPart
import kotlin.math.abs

fun main() {
    val day = 1
    println("\n===== AOC 2024 : $day =====\n")

    //Test input
    val testInput = readTest(day)
    checkTest(::part1, testInput, 11)
    checkTest(::part2, testInput, 31)

    val input = readInput(day)
    runPart(::part1, input)  // Part 1: 1722302
    runPart(::part2, input)  // Part 2: 20373490
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
