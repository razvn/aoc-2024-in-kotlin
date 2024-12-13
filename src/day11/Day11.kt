package day11

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day11
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 183620
   // runPart(day::part2, input)  // Part 2:
}

object Day11 : Day<Long, Long, List<Long>> {
    override fun decodeData(input: List<String>): List<Long> {
        return input.flatMap { it.split(" ").map { it.toLong() } }
    }

    override fun part1(input: List<String>): Long {
        val data = decodeData(input)

        val resp = blink(data, 25)
        return resp
    }

    private fun String.split(): List<String> {
        val half = this.length / 2
        val first = this.substring(0, half).toLong().toString()
        val second = this.substring(half).toLong().toString()
        return listOf(first, second)
    }

    fun blink(stones: List<Long>, nb: Int): Long {

        var newStones = stones
        val cacheCalc = mutableMapOf<Long, List<Long>>()

        repeat(nb) {
            newStones = newStones.flatMap { stone ->
                cacheCalc.getOrPut(stone) {
                    when {
                        stone == 0L -> listOf(1)
                        stone.toString().length % 2 == 0 -> stone.toString().split().map { it.toLong() }
                        else -> listOf((stone * 2024))
                    }
                }
            }
        }

        return newStones.size.toLong()
    }

    fun s(stones: List<Long>, ): Long {
        val cacheMap = mutableMapOf<Long, Long>()
        var count = 0L
        stones.forEach { stone ->
            val res = cacheMap.getOrPut(stone) { blink(listOf(stone), nb) }
            count += res
        }

        return count
    }


    // To figure out
    fun blinkCount(stones: List<Long>, nb: Int): Long {

        val cacheMap = mutableMapOf<Long, Long>()
        val count = 0L
        stones.forEach { stone ->
            val res = cacheMap.getOrPut(stone) { blink(listOf(stone), nb) }
            res
        }

        return count
    }

    override fun part2(input: List<String>): Long {
        val data = decodeData(input)

        val resp = blinkCount(data, 75)
        return resp
    }
}
