package day03

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day03
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 162813399
    runPart(day::part2, input)  // Part 2: 53783319
}


object Day03 : Day<Int, Int, String> {
    override fun decodeData(input: List<String>): String {
        return input.joinToString()
    }

    private val regex = """mul\((?<first>\d+),(?<second>\d+)\)""".toRegex()

    override fun part1(input: List<String>) = calculate(decodeData(input))

    override fun part2(input: List<String>) = calculate(removeDisabled(decodeData(input)))

    private fun calculate(input: String): Int {
        val matches = regex.findAll(input)
        return matches.sumOf{
            val i = it.groups["first"]!!.value.toInt()
            val j = it.groups["second"]!!.value.toInt()
            i * j
        }
    }

    private tailrec fun removeDisabled(input: String, acc: String = ""): String {
        val dontIndex = input.indexOf("don't()")
        if (dontIndex == -1) {
            return "$acc$input"
        }
        val validPart = input.substring(0, dontIndex)

        val doIndex = input.indexOf("do()", dontIndex)

        val remainPart = if (doIndex == -1) "" else input.substring(doIndex+4)
        return removeDisabled(remainPart, "$acc$validPart")
    }
}
