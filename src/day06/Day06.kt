package day06

import Day
import day06.Day06.Direction.UP
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day06
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 4903
    runPart(day::part2, input)  // Part 2: 1911
}


object Day06 : Day<Int, Int, List<MutableList<Char>>> {
    override fun decodeData(input: List<String>): List<MutableList<Char>> {

        return input.map { it.toMutableList() }
    }


    class Playground(private val playground: List<MutableList<Char>>) {
        fun getChar(point: Point): Char {
            if (point.x < 0 || point.x >= playground[0].size || point.y < 0 || point.y >= playground.size) {
                return '#'
            }
            return playground[point.y][point.x]
        }

        fun setChar(point: Point, char: Char) {
            playground[point.y][point.x] = char
        }

        fun moveGuard(guard: Guard): Guard? {
            val nextPosition = guard.nextPosition()
            if (nextPosition.x < 0 || nextPosition.x >= playground[0].size || nextPosition.y < 0 || nextPosition.y >= playground.size) {
                return null
            }
            val newGuard = if (getChar(nextPosition) == '#') {
                val newDirection = guard.direction.turnRight()
                guard.copy(direction = newDirection)
            } else {
                setChar(guard.position, 'X')
                guard.copy(position = nextPosition)
            }
            setChar(newGuard.position, newGuard.direction.symbol)

            return newGuard
        }

        fun locateGuard(direction: Direction): Guard {
            val vertical = playground.indexOfFirst { it.contains(direction.symbol) }
            val horizontal = playground[vertical].indexOf(direction.symbol)
            return Guard(Point(horizontal, vertical), direction)
        }

        fun print() {
            playground.forEach {
                println(it.joinToString(""))
            }
        }
    }

    override fun part1(input: List<String>): Int {
        val playground = Playground(decodeData(input))

        return runFun(playground).second
    }

    enum class ExitType {
        EXIT, LOOP
    }

    private fun runFun(playground: Playground): Pair<ExitType, Int> {
        val guardLocations = mutableSetOf<Guard>()
        var guard = playground.locateGuard(UP)
        guardLocations.add(guard)
        val exitType: ExitType
        while (true) {
            // if we have already been here and we are and moving in the same direction
            val newGuard = playground.moveGuard(guard)
            guard = if (newGuard != null) newGuard else {
                exitType = ExitType.EXIT
                break
            }
            if (guardLocations.contains(guard)) {
                exitType = ExitType.LOOP
                break
            } else {
                guardLocations.add(guard)
            }
        }

        return exitType to guardLocations.map { it.position }.toSet().size
    }

    override fun part2(input: List<String>): Int {
        val ret = decodeData(input).mapIndexed { y, data ->
            data.mapIndexed { x, char ->
                if (char == '.') {
                    val playground = Playground(decodeData(input))
                    playground.setChar(Point(x, y), '#')
                    val (returnType, _) = runFun(playground)
                    if (returnType == ExitType.LOOP) {
                        1
                    } else 0
                } else 0
            }
        }.sumOf { it.sum() }
        return ret
    }


    data class Point(val x: Int, val y: Int)

    enum class Direction(val symbol: Char, val movevertical: Int, val movehorizontal: Int) {
        UP('^', -1, 0),
        DOWN('v', 1, 0),
        RIGHT('>', 0, 1),
        LEFT('<', 0, -1);

        fun turnRight(): Direction {
            return when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }
        }
    }

    data class Guard(val position: Point, val direction: Direction) {
        fun nextPosition(): Point {
            return Point(
                x = this.position.x + this.direction.movehorizontal,
                y = this.position.y + this.direction.movevertical,
            )
        }
    }
}
