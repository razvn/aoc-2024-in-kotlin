package day04

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day04
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 2496
    runPart(day::part2, input)  // Part 2: 1967
}

object Day04 : Day<Int, Int, List<String>> {
    override fun decodeData(input: List<String>): List<String> {

        return input
    }

    override fun part1(input: List<String>): Int {
        return input.mapIndexed { i, data -> xmas(i, data, input) }.sum()
    }

    override fun part2(input: List<String>): Int {
        // we need to drop the first and last line / char as A cannot be on the border
        return input.drop(1).dropLast(1).mapIndexed { lineIdx, line ->
            line.substring(1, line.lastIndex).mapIndexed { colIdx, char ->
                if (char == 'A') checkPart2(lineIdx + 1, colIdx + 1, input) else 0
            }.sum()
        }.sum()
    }

    private fun xmas(lineIndex: Int, line: String, input: List<String>): Int {
        return line.mapIndexedNotNull { colIndex, char ->
            if (char == 'X') checkOperation(lineIndex, colIndex, input) else null
        }.sum()
    }

    private fun checkOperation(line: Int, col: Int, input: List<String>): Int {
        return Operation.entries.map {
            applyOperation(line, col, it, input, 3)
        }.count { it == "MAS" } //already checked that the first letter is X
    }

    enum class Operation(val lineInc: Int, val colInc: Int) {
        E(0, 1),
        W(0, -1),
        N(-1, 0),
        S(1, 0),
        NE(-1, 1),
        NW(-1, -1),
        SE(1, 1),
        SW(1, -1);
    }

    private fun List<String>.charAtLineCol(lineIndex: Int, colIndex: Int): Char {
        return this[lineIndex][colIndex]
    }

    private fun applyOperation(line: Int, col: Int, op: Operation, input: List<String>, len: Int = 3): String {
        return (1..len).mapNotNull {
            val newLine = line + op.lineInc * it
            val newCol = col + op.colInc * it
            input.getOrNull(newLine)?.getOrNull(newCol)
        }.joinToString("")
    }

    private fun checkPart2(lineIndex: Int, colIndex: Int, input: List<String>): Int {
        val positions = listOf(
            input.charAtLineCol(lineIndex - 1, colIndex - 1) to input.charAtLineCol(lineIndex + 1, colIndex + 1),
            input.charAtLineCol(lineIndex - 1, colIndex + 1) to input.charAtLineCol(lineIndex + 1, colIndex - 1)
        )
        return if (positions.all { (a, b) -> (a == 'M' && b == 'S') || (a == 'S' && b == 'M') }) 1 else 0
    }
}
