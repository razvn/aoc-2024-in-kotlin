package day02

import Day
import readInput
import runPart

fun main() {
    val day = Day02
    println("\n===== AOC 2024 : ${day.nb} =====\n")

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
        val dec = data.count { isDecrease(it) }
        return inc + dec
    }

    private fun isDecrease(list: List<Int>): Boolean {
        for (i in 0..<list.size - 1) {
            if (list[i] - (list[i + 1]) in 1..3) {
                continue
            } else {
                return false
            }
        }
        return true
    }


    private fun isIncrease(list: List<Int>): Boolean {
        for (i in 0..<list.size - 1) {
            if ((list[i + 1] - list[i]) in 1..3) {
                continue
            } else {
                return false
            }
        }
        return true
    }

    override fun part2(input: List<String>): Int {
        val data = decodeData(input)
        val inc = data.count { isDecreaseTolerate(it) }
        val dec = data.count { isIncreaseTolerate(it) }
        return inc + dec
    }

    private fun isDecreaseTolerate(list: List<Int>): Boolean {
        for (i in 0..<list.size - 1) {
            if (list[i] - (list[i + 1]) in 1..3) {
                continue
            } else {
                return isDecrease(list.subList(0, i) + list.subList(i + 1, list.size))
                        || isDecrease(list.subList(0, i + 1) + list.subList(i + 2, list.size))
            }
        }
        return true
    }


    private fun isIncreaseTolerate(list: List<Int>): Boolean {
        for (i in 0..< list.size - 1) {
            if ((list[i + 1] - list[i]) in 1..3) {
                continue
            } else {
                return isIncrease(list.subList(0, i) + list.subList(i + 1, list.size)) ||
                        isIncrease(list.subList(0, i+1) + list.subList(i + 2, list.size))
            }
        }
        return true
    }
}
