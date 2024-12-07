package day07

import Day
import day07.Day07.Operation.*
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day07
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 10741443549536
    runPart(day::part2, input)  // Part 2: 500335179214836
}

object Day07 : Day<Long, Long, Map<Long, List<Long>>> {
    override fun decodeData(input: List<String>): Map<Long, List<Long>> {
        return input.map {
            val (key, value) = it.split(":")
            val values = value.trim().split(" ").map {
                it.toLong()
            }
            Pair(key.toLong(), values)
        }.toMap()
    }

    override fun part1(input: List<String>): Long {
        val data = decodeData(input)
        val validLines = data.entries.mapNotNull { (key, values) ->
            calc(values, key, listOf(ADD, MULT), emptyList())
        }.toMap()
        // validLines.forEach { println("${it.key} = ${it.value}") }
        return validLines.keys.sumOf { it }
    }

    tailrec fun calc(
        nbs: List<Long>,
        result: Long,
        operations: List<Operation>,
        acc: List<Pair<Long, String>>
    ): Pair<Long, String>? {
        return if (nbs.isEmpty()) acc.firstOrNull { it.first == result }
        else {
            val a = nbs.first()
            val rest = nbs.drop(1)
            val newAcc = if (acc.isEmpty()) listOf(a to "")
            else acc.flatMap { accValue ->
                operations.map { op ->
                    op.func(accValue.first, a) to "${accValue.second}${op.op}$a"
                }
            }.filter { it.first <= result }
            return calc(rest, result, operations, newAcc)
        }
    }

    override fun part2(input: List<String>): Long {
        val data = decodeData(input)
        val validLines = data.entries.mapNotNull { (key, values) ->
            calc(values, key, listOf(ADD, MULT, OR), emptyList())
        }.toMap()
        // validLines.forEach { println("${it.key} = ${it.value}") }
        return validLines.keys.sumOf { it }
    }

    enum class Operation(val op: String, val func: (Long, Long) -> Long) {
        ADD(" + ", { a, b -> a + b }),
        MULT(" * ", { a, b -> a * b }),
        OR(" || ", { a, b -> "$a$b".toLong() })
    }
}
