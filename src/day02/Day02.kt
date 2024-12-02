package day02

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day02
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 559
    runPart(day::part2, input)  // Part 2: 601
}

object Day02 : Day<Int, Int, List<List<Int>>> {
    override fun decodeData(input: List<String>): List<List<Int>> {
        return input.map { it.split(" ").map(String::toInt) }
    }

    override fun part1(input: List<String>): Int {
        val data = decodeData(input)
        val inc = data.count { isIncrease(it) }
        val dec = data.count { isIncrease(it.reversed()) }
        return inc + dec
    }

    override fun part2(input: List<String>): Int {
        val data = decodeData(input)
        val inc = data.count { isIncreaseTolerate(it) }
        val dec = data.count { isIncreaseTolerate(it.reversed()) }
        return inc + dec
    }

    private fun isIncrease(list: List<Int>): Boolean {
        return list.zipWithNext().all { (a, b) -> b - a in 1..3 }
    }

    private fun isIncreaseTolerate(list: List<Int>): Boolean {
        for (i in 0..< list.size - 1) {
            val diff = list[i + 1] - list[i]
            if (diff in 1..3) {
                continue
            } else {
                return isIncrease(list.toMutableList().apply { removeAt(i) })
                        || isIncrease(list.toMutableList().apply { removeAt(i + 1) })
            }
        }
        return true
    }
}
