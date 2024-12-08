package day08

import Coordinates
import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day08
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 214
    runPart(day::part2, input)  // Part 2: 809
}


object Day08 : Day<Int, Int, Day08.DayInput> {

    data class DayInput(val data: Map<Char, List<Coordinates>>, val maxX: Int, val maxY: Int)

    override fun decodeData(input: List<String>): DayInput {
        val mapCharCoordinates = input.flatMapIndexed { y: Int, s: String ->
            s.mapIndexedNotNull { x, c ->
                if (c == '.') null
                else c to Coordinates(x, y) // Map
            }
        }.groupBy { it.first }
            .mapValues { it.value.map { it.second } }

        return DayInput(mapCharCoordinates, input[0].length, input.size)
    }

    override fun part1(input: List<String>): Int {
        val (data, maxX, maxY) = decodeData(input)

        val antinodes = data.mapValues { createAntiNodes(it.value, emptyList(), maxX - 1, maxY - 1) }

        /*
          var prevInput = input
          antinodes.entries.map {
              prevInput = drawBoard(prevInput, it.value.toSet())
              prevInput
          }
          prevInput.forEach { println(it) }
          println()

         */
        return antinodes.values.flatten().toSet().count()
    }

    fun printBoard(input: List<String>, value: List<Coordinates>) {
        val board = input.map { it.toCharArray().toMutableList() }
        value.forEach { board[it.y][it.x] = '#' }
        board.forEach { println(it.joinToString("")) }
        println()
    }

    private fun drawBoard(input: List<String>, value: Set<Coordinates>): List<String> {
        val board = input.map { it.toCharArray().toMutableList() }
        value.forEach { board[it.y][it.x] = '#' }
        return board.map { it.joinToString("") }
    }

    private fun createAntiNodes(
        value: List<Coordinates>,
        acc: List<Coordinates>,
        maxX: Int,
        maxY: Int
    ): List<Coordinates> {
        if (value.isEmpty()) return acc
        val curVal = value.first()
        val remain = value.drop(1)
        val anti = remain.flatMap { antiNodesFor(curVal, it, maxX, maxY) }
        return createAntiNodes(remain, acc + anti, maxX, maxY)
    }

    private fun antiNodesFor(
        value1: Coordinates,
        value2: Coordinates,
        maxSizeX: Int,
        maxSizeY: Int
    ): List<Coordinates> {
        val distX = value1.xDistanceTo(value2)
        val distY = value1.yDistanceTo(value2)


        val p1 = value1.add(distX, distY)
        val p2 = value2.add(-distX, -distY)

        return listOf(p1, p2)
            .filter { it.areValidBounds(0..maxSizeX, 0..maxSizeY) }
    }

    private fun createAntiNodesPart2(
        value: List<Coordinates>,
        acc: Set<Coordinates>,
        maxX: Int,
        maxY: Int
    ): Set<Coordinates> {
        if (value.isEmpty()) return acc
        val curVal = value.first()
        val remain = value.drop(1)
        val anti = remain.flatMap { antiNodesForPart2(curVal, it, maxX, maxY) }
        return createAntiNodesPart2(remain, acc + anti, maxX, maxY)
    }

    private fun antiNodesForPart2(
        value1: Coordinates,
        value2: Coordinates,
        maxSizeX: Int,
        maxSizeY: Int
    ): List<Coordinates> {
        val dist = value1.distanceTo(value2)

        val finalList = mutableListOf<Coordinates>()

        var factor = 1
        while ((dist * factor).abs().areValidBounds(0..maxSizeX, 0..maxSizeY)) {
            val facX = dist.x * factor
            val facY = dist.y * factor
            val p1 = value1.add(facX, facY)
            val p2 = value2.add(-facX, -facY)
            factor++
            val otherAntinodes = listOf(p1, p2)
                .filter { it.areValidBounds(0..maxSizeX, 0..maxSizeY) }
            finalList.addAll(otherAntinodes)
            if (otherAntinodes.isNotEmpty()) finalList.addAll(listOf(value1, value2))
        }

        return finalList
    }

    override fun part2(input: List<String>): Int {

        val (data, maxX, maxY) = decodeData(input)

        val antinodes = data.mapValues {
            val nodes = createAntiNodesPart2(it.value, emptySet(), maxX - 1, maxY - 1)
            if (nodes.isNotEmpty()) nodes + it.value else nodes
        }
        /*
        var prevInput = input
        antinodes.entries.map {
            prevInput = drawBoard(prevInput, it.value)
            prevInput
        }
        prevInput.forEach { println(it) }
        println()
         */
        return antinodes.values.flatten().toSet().count()
    }
}
