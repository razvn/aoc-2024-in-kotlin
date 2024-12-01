package day01

import Day
import readInput
import runPart
import kotlin.math.abs

fun main() {
    val day = Day01
    println("\n===== AOC 2024 : ${day.nb} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 1722302
    runPart(day::part2, input)  // Part 2: 20373490
}

object Day01 : Day<Int, Int, Pair<List<Int>, List<Int>>> {
    override fun decodeData(input: List<String>): Pair<List<Int>, List<Int>> {
        val (firstList, secondList) = input.map { it.split("   ") }
            .map { it.first().toInt() to it.last().toInt() }
            .unzip()

        return firstList.sorted() to secondList.sorted()
    }

    override fun part1(input: List<String>): Int {
        val (firstList, secondList) = decodeData(input)

        return firstList.zip(secondList) { a, b -> abs(a - b) }.sum()
    }

    override fun part2(input: List<String>): Int {
        val (firstList, secondList) = decodeData(input)
        val secondListCount = secondList.groupingBy { it }.eachCount()

        return firstList.sumOf { it * secondListCount.getOrDefault(it, 0) }
    }
}
