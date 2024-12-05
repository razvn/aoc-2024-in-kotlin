package day03

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day03bis
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 162813399
    runPart(day::part2, input)  // Part 2: 53783319
}


object Day03bis : Day<Int, Int, List<String>> {
    override fun decodeData(input: List<String>): List<String> {

        return input
    }

    private val regex = """mul\((?<first>\d+),(?<second>\d+)\)""".toRegex()

    fun calculate(input: String): Int {
        val matches = regex.findAll(input)
        return matches.sumOf{
            val i = it.groups["first"]!!.value.toInt()
            val j = it.groups["second"]!!.value.toInt()
            i * j
        }
    }

    override fun part1(input: List<String>): Int {
        val data = decodeData(input)
        val list = data.map(::calculate)
        println(list)
        return list.sum()
    }

    tailrec fun removeDisabled(input: String, prefix: String = ""): String {
        val dontIndex = input.indexOf("don't()")
        if (dontIndex == -1) {
            return prefix + input
        }
        val doIndex = input.indexOf("do()", dontIndex)
        val beforeDont = input.substring(0, dontIndex)

        val afterDo = if (doIndex == -1) "" else input.substring(doIndex)
        return removeDisabled(afterDo+4, beforeDont)
    }

    fun removeDont(input: String): String {
        val dontIndex = input.indexOf("don't()")
        if (dontIndex == -1) {
            return input
        }
        val doIndex = input.indexOf("do()", dontIndex)
        val beforeDont = input.substring(0, dontIndex)

        val afterDo = if (doIndex == -1) "" else input.substring(doIndex)
        return beforeDont + removeDont(afterDo)
    }

    override fun part2(input: List<String>): Int {
        // val data = decodeData(readInput(nb,"_input2"))
        val data = decodeData(input).joinToString()

        val removeDont = listOf(data).map {
            // removeDont(it)
            removeDisabled(it)
        }
        return part1(removeDont)
    }
}
