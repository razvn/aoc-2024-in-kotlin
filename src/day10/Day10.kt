package day10

import Day
import readInput
import runPart
import java.time.LocalDateTime
import kotlin.math.abs

fun main() {
    val day = Day10
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 538
    runPart(day::part2, input)  // Part 2: 1110
}

object Day10 : Day<Int, Int, Set<Day10.Point>> {

    data class Point(val x: Int, val y: Int, val height: Int, val nextPoints: Set<Point> = emptySet()) {
        fun isValidToPoint(other: Point): Boolean {
            return this.height == other.height + 1
                    && ((abs(this.x - other.x) == 1 && abs(this.y - other.y) == 0)
                    || (abs(this.x - other.x) == 0 && abs(this.y - other.y) == 1))
        }

        val fullNb: Int by lazy { if (this.height == 9) 1 else this.nextPoints.sumOf { it.fullNb } }

        val leadsToFull: Boolean by lazy { this.height == 9 || this.nextPoints.any { it.leadsToFull } }
    }

    override fun decodeData(input: List<String>): Set<Point> {
        return input.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, value ->
                if (value != '.') Point(x, y, value.digitToInt())
                else null
            }
        }.toSet()
    }

    override fun part1(input: List<String>): Int {
        val map = decodeData(input)
        val startingPoints = map.filter { it.height == 0 }
            .map { it.copy(nextPoints = trailsFor(it, map)) }

        val heads = startingPoints.map { it to full(it) }.toMap()


        return heads.values.sumOf { it.size }
    }

    fun full(point: Point, acc: Set<Point> = emptySet()): Set<Point> {
        if (point.height == 9) return acc + point
        else return point.nextPoints.flatMap { full(it, acc) }.toSet()
    }

    fun countHeads(point: Point, acc: Int = 0): Int {
        return when {
            point.nextPoints.isEmpty() -> 0
            point.height == 8 -> point.nextPoints.count { it.height == 9 }
            else -> point.nextPoints.sumOf { countHeads(it, acc) }
        }
    }

    fun trailsFor(point: Point, map: Collection<Point>): Set<Point> {
        if (point.height == 9 || map.isEmpty()) return emptySet()

        val validNextPoints = map.filter { it.isValidToPoint(point) }

        return validNextPoints.map { it.copy(nextPoints = trailsFor(it, map)) }
            .filter { it.leadsToFull }
            .toSet()
    }

    override fun part2(input: List<String>): Int {
        val map = decodeData(input)
        val startingPoints = map.filter { it.height == 0 }
            .map { it.copy(nextPoints = trailsFor(it, map)) }


        val heads = startingPoints.map { it to it.fullNb }.toMap()

        return heads.values.sum()
    }
}
