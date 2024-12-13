package day13

import Coordinates
import Day
import readInput
import runPart
import java.time.LocalDateTime
import kotlin.math.min

fun main() {
    val day = Day13
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 27105
    runPart(day::part2, input)  // Part 2:
}

data class Machine(val a: Coordinates, val b: Coordinates, val prize: Coordinates) {

    fun calc(): Int {
        val optimizA = calcScore(a, b, prize, 0, 3, 1)
        val optimizB = calcScore(b, a, prize, 0, 1, 3)

        return min(optimizA, optimizB)
    }

    tailrec fun calcScore(a: Coordinates, b: Coordinates, prize: Coordinates, i: Int = 0, factorA: Int, factorB: Int): Int {
        val divAX = prize.x / a.x
        val divAY = prize.y / a.y

        val divA = min(divAX, divAY) - i
        val modAX = prize.x - a.x * divA
        val modAY = prize.y - a.y * divA

        val divBX = modAX / b.x
        val modBX = modAX % b.x
        val divBY = modAY / b.y
        val modBY = modAY % b.y

        return when {
            divA < 0 -> 0
            modBX == 0 && modBY == 0 && divBX == divBY -> divA * factorA + divBX * factorB
            else -> calcScore(a, b, prize, i + 1, factorA, factorB)
        }
    }
}

object Day13 : Day<Int, Long, List<Machine>> {
    override fun decodeData(input: List<String>): List<Machine> {
        return input
            .filter { it.isNotBlank() }
            .chunked(3)
            .map { toMachine(it) }
    }

    private fun toMachine(input: List<String>): Machine {
        val a = toButton(input.first { it.startsWith("Button A:") })
        val b = toButton(input.first { it.startsWith("Button B:") })
        val prize = toPrice(input.first { it.startsWith("Prize:") })
        return Machine(a, b, prize)
    }

    private fun toButton(input: String): Coordinates {
        val x = input.substringAfter("X+").substringBefore(",").toInt()
        val y = input.substringAfter("Y+").toInt()
        return Coordinates(x, y)
    }

    private fun toPrice(input: String): Coordinates {
        val x = input.substringAfter("X=").substringBefore(",").toInt()
        val y = input.substringAfter("Y=").toInt()
        return Coordinates(x, y)
    }

    override fun part1(input: List<String>): Int {
        val data = decodeData(input)

        return data.sumOf {
            it.calc()
        }
    }


    override fun part2(input: List<String>): Long {
        val data = decodeData(input)
            .map { MachineLong.fromMachine(it) }

        return data.sumOf {
            it.pressButtons()
        }
    }


    data class CoordinatesLong(val x: Long, val y: Long) {
        companion object {
            fun fromCoordinate(coordinate: Coordinates, factor: Long = 0L): CoordinatesLong =
                CoordinatesLong(coordinate.x.toLong() + factor, coordinate.y.toLong() + factor)
        }
    }

    data class MachineLong(val a: CoordinatesLong, val b: CoordinatesLong, val prize: CoordinatesLong) {
        companion object {
            fun fromMachine(m: Machine) = MachineLong(
                CoordinatesLong.fromCoordinate(m.a),
                CoordinatesLong.fromCoordinate(m.b),
                CoordinatesLong.fromCoordinate(m.prize, 10000000000000L)
            )
        }

        // thanks to https://www.reddit.com/r/adventofcode/comments/1hd7irq/2024_day_13_an_explanation_of_the_mathematics/
        fun pressButtons(): Long {
            val det = a.x * b.y - a.y * b.x
            val buttonA = (prize.x * b.y - prize.y * b.x) / det
            val buttonB = (a.x * prize.y - a.y * prize.x) / det
            return if (a.x * buttonA + b.x * buttonB == prize.x && a.y * buttonA + b.y * buttonB == prize.y) {
                buttonA * 3 + buttonB
            } else 0
        }
    }
}

