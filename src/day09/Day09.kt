package day09

import Day
import day09.Day09.File
import day09.Day09.Space
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day09
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 6415184586041
    runPart(day::part2, input)  // Part 2: 6436819084274
}

object Day09 : Day<Long, Long, Pair<MutableList<File>, MutableList<Space>>> {

    data class File(var index: Int, val value: Int, val repetition: Int = 1)
    data class Space(var index: Int, val value: Int = 0, val repetition: Int = 1)

    override fun decodeData(input: List<String>): Pair<MutableList<File>, MutableList<Space>> {
        var id = 0
        val files = mutableListOf<File>()
        val spaces = mutableListOf<Space>()

        var i = 0
        input.first().forEachIndexed { idxInput, c ->
            val rep = c.digitToInt()
            val isFile = idxInput % 2 == 0

            repeat(rep) { j ->
                if (isFile) {
                    files.add(File(j + i, id))
                } else {
                    spaces.add(Space(j + i))
                }
            }
            if (isFile) id++
            i += rep
        }
        return files to spaces
    }

    data class Item(val value: Int, val repetition: Int)
    fun decodeData2(input: List<String>): MutableList<Item> {
        var id = 0

        val data = input.first().mapIndexed { idxInput, s ->
                val rep = s.digitToInt()
                val isFile = idxInput % 2 == 0

                if (isFile) {
                    Item(id, rep)
                } else {
                    Item(-1, rep)
                }.also { if (isFile) id++ }
            }.toMutableList()
        return data
    }

    fun swap(space: Space, file: File) {
        val tmpIdx = space.index
        space.index = file.index
        file.index = tmpIdx
    }

    override fun part1(input: List<String>): Long {
        val (files, spaces) = decodeData(input)

        while (true) {
            val firstSpace = spaces.minBy { it.index }
            val lastFile = files.maxBy { it.index }
            if (firstSpace.index >= lastFile.index) break

            swap(firstSpace, lastFile)
        }

        val checksum = files.sumOf { it.index.toLong() * it.value }

        return checksum
    }

    override fun part2(input: List<String>): Long {
        val data = decodeData2(input)

        var fileIdMax = data.filter { it.value >= 0 }.maxOf { it.value }

        while (fileIdMax >= 1) {
            val fileIndex = data.indexOfFirst { it.value == fileIdMax }
            val spaceIndex = data.indexOfFirst { it.value == -1 && it.repetition >= data[fileIndex].repetition }

            if(spaceIndex >= 0 && fileIndex > spaceIndex) {
                val fileRep = data[fileIndex].repetition
                val spaceRep = data[spaceIndex].repetition
                val diff = spaceRep - fileRep
                when {
                    diff > 0 -> {
                        data[fileIndex] = Item(-1, fileRep)
                        data[spaceIndex] = Item(fileIdMax, spaceRep - diff)
                        data.add(spaceIndex + 1, Item(-1, diff))
                    }
                    diff == 0 -> {
                        val tmp = data[fileIndex]
                        data[fileIndex] = data[spaceIndex]
                        data[spaceIndex] = tmp
                    }
                }
            }
            fileIdMax--
        }
        val finalData = data.map { item ->
            List(item.repetition) { (if (item.value >=0 ) item.value else ".").toString() } }
            .flatten()
            .toMutableList()

        return finalData.foldIndexed(0L) { index, acc, c ->
            when (c) {
                "." -> acc
                else -> acc + (c.toLong() * index)
            }
        }
    }
}
