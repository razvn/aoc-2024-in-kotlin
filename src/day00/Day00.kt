package day00

import Day
import checkTest
import readInput
import readTest
import runPart

fun main() {
    val day = Day00
    println("\n===== AOC 2024 : ${day.nb} =====\n")

    //Test input
    val testInput = readTest(day.nb)
    checkTest(day::part1, testInput, 0)
    checkTest(day::part2, testInput, 0)

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1:
    runPart(day::part2, input)  // Part 2:
}


object Day00 : Day<Int, Int, List<String>> {
    override fun decodeData(input: List<String>): List<String> {

        return input
    }

    override fun part1(input: List<String>): Int {

        return 0
    }

    override fun part2(input: List<String>): Int {

        return 0
    }
}
