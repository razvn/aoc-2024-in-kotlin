package day01

import readInput
import readTest
import kotlin.math.abs

private fun decodeData(input: List<String>): Pair<List<Int>, List<Int>> {
    val lists = input.map { it.split("   ") }
    val fistList = lists.map { it.first().toInt() }.sorted()
    val secondList = lists.map { it.last().toInt() }.sorted()
    return fistList to secondList
}

private fun part1(input: List<String>): Int {
    val (fistList, secondList) = decodeData(input)

    val result = fistList.mapIndexed { index, s ->
        abs(s - secondList[index])
    }

    return result.sum()
}

private fun part2(input: List<String>): Int {
    val (fistList, secondList) = decodeData(input)

    val secondListCount = secondList.groupingBy { it }.eachCount()

    val result = fistList.map {
        val similarity = secondListCount.getOrDefault(it, 0)
        it * similarity
    }
    return result.sum()
}

fun main() {
    val day = "Day01"
    // test if implementation meets criteria from the description, like:
    val testInput = readTest(day)
    check(part1(testInput) == 11)
    check(part2(testInput) == 3)

    val input = readInput(day)
    // Part 1: 1722302
    // Part 2: 20373490
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

