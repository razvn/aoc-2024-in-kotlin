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
        return input.mapIndexed {i, data -> xmas(i, data, input) }.sum()
    }

    private fun xmas(lineIndex: Int, line: String, input: List<String>): Int {
        return line.mapIndexed { colIndex, c ->
            when(c) {
                'X' -> checkX(lineIndex, colIndex, line, input)
                else -> 0
            }
        }.sum()
    }

    fun xmasEst(colIndex: Int, line: String): Boolean {
        return line.substring(colIndex).startsWith("XMAS")
    }

    fun xmasWest(colIndex: Int, line: String): Boolean {
        return line.substring(0, colIndex+1).endsWith("SAMX")
    }

    fun xmasNord(lineIndex: Int, colIndex: Int, input: List<String>): Boolean {
        if (lineIndex < 3) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex-1][colIndex]}${input[lineIndex-2][colIndex]}${input[lineIndex-3][colIndex]}"
        return text == "XMAS"
    }


    fun xmasSouth(lineIndex: Int, colIndex: Int, input: List<String>): Boolean {
        if (lineIndex > input.size - 4) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex+1][colIndex]}${input[lineIndex+2][colIndex]}${input[lineIndex+3][colIndex]}"
        return text == "XMAS"
    }

    fun xmasNE(lineIndex: Int, colIndex: Int, line: String, input: List<String>): Boolean {
        if (lineIndex < 3 || colIndex > line.length - 4) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex-1][colIndex+1]}${input[lineIndex-2][colIndex+2]}${input[lineIndex-3][colIndex+3]}"
        return text == "XMAS"
    }

    fun xmasNW(lineIndex: Int, colIndex: Int, input: List<String>): Boolean {
        if (lineIndex < 3 || colIndex < 3) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex-1][colIndex-1]}${input[lineIndex-2][colIndex-2]}${input[lineIndex-3][colIndex-3]}"
        return text == "XMAS"
    }

    fun xmasSE(lineIndex: Int, colIndex: Int, line: String, input: List<String>): Boolean {
        if (lineIndex > input.size - 4 || colIndex > line.length - 4) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex+1][colIndex+1]}${input[lineIndex+2][colIndex+2]}${input[lineIndex+3][colIndex+3]}"
        return text == "XMAS"
    }

    fun xmasSW(lineIndex: Int, colIndex: Int, input: List<String>): Boolean {
        if (lineIndex > input.size - 4 || colIndex < 3) return false
        val text = "${input[lineIndex][colIndex]}${input[lineIndex+1][colIndex-1]}${input[lineIndex+2][colIndex-2]}${input[lineIndex+3][colIndex-3]}"
        return text == "XMAS"
    }

    fun checkX(lineIndex: Int, colIndex: Int, line: String, input: List<String>): Int {
        val est = xmasEst(colIndex, line)
        val west = xmasWest(colIndex, line)
        val nord = xmasNord(lineIndex, colIndex, input)
        val south = xmasSouth(lineIndex, colIndex, input)
        val ne = xmasNE(lineIndex, colIndex, line, input)
        val nw = xmasNW(lineIndex, colIndex, input)
        val se = xmasSE(lineIndex, colIndex, line, input)
        val sw = xmasSW(lineIndex, colIndex, input)

        return listOf(est, west, nord, south, ne, nw, se, sw).count { it }
    }

    fun checkA(lineIndex: Int, colIndex: Int, input: List<String>): Int {
        val nw = input[lineIndex - 1][colIndex -1]
        val ne = input[lineIndex - 1][colIndex + 1]
        val sw = input[lineIndex + 1][colIndex - 1]
        val se = input[lineIndex + 1][colIndex + 1]
        return if (((nw == 'M' && se == 'S') || (nw == 'S' && se == 'M')) &&
            ((ne == 'M' && sw == 'S') || (ne == 'S' && sw == 'M'))) 1 else 0
    }

    override fun part2(input: List<String>): Int {
        return (1..input.size - 2).sumOf { lineIdx ->
            (1..input[lineIdx].length - 2).sumOf { colIdx ->
                val char = input[lineIdx][colIdx]
                if (char == 'A') checkA(lineIdx, colIdx, input)
                else 0
            }
        }
    }
}
