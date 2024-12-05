package day05

import Day
import readInput
import runPart
import java.time.LocalDateTime

fun main() {
    val day = Day05
    println("\n===== AOC 2024 : ${day.nb} : ${LocalDateTime.now()} =====\n")

    val input = readInput(day.nb)
    runPart(day::part1, input)  // Part 1: 5747
    runPart(day::part2, input)  // Part 2: 5502
}


object Day05 : Day<Int, Int, Pair<List<Day05.OrderRule>, List<List<Int>>>> {
    override fun decodeData(input: List<String>): Pair<List<OrderRule>, List<List<Int>>> {
        val rules = mutableListOf<OrderRule>()
        val pages = mutableListOf<List<Int>>()
        input.forEach { line ->
            when {
                line.contains("|") -> rules.add(
                    line.split("|").let { OrderRule(it.first().toInt(), it.last().toInt()) })

                line.contains(",") -> pages.add(line.split(",").map { it.toInt() })
            }
        }

        return rules to pages
    }

    data class OrderRule(val first: Int, val second: Int)

    override fun part1(input: List<String>): Int {
        val (rules, pagesList) = decodeData(input)

        val validPages = pagesList.filter { pagesAreValid(it, rules) }
        return validPages.sumOf {
            it[it.size / 2]
        }
    }

    fun pagesAreValid(pages: List<Int>, rules: List<OrderRule>): Boolean {
        val rulesForPages = rules.filter { pages.contains(it.first) && pages.contains(it.second) }
        return pages.all { currentPage ->
            val rulesForCurrentPage = rulesForPages.filter { it.first == currentPage || it.second == currentPage }
            validateRulesForCurrentPage(currentPage, rulesForCurrentPage, pages)
        }
    }

    private fun validateRulesForCurrentPage(page: Int, rules: List<OrderRule>, pages: List<Int>): Boolean {
        return rules.all { rule ->
            val otherPage = if (rule.first == page) rule.second else rule.first
            val isBefore = rule.first == page
            val index = pages.indexOf(page)
            val otherIndex = pages.indexOf(otherPage)
            if (isBefore) index < otherIndex else index > otherIndex
        }
    }

    override fun part2(input: List<String>): Int {
        val (rules, pagesList) = decodeData(input)

        val invalidPages = pagesList.filter { pagesAreValid(it, rules).not() }
        val orderdPages = invalidPages.map { pages ->
            applyRules(pages, rules)
        }
        return orderdPages.sumOf { it[it.size / 2] }
    }

    fun sortItems(list: List<Int>, rules: List<OrderRule>): List<Int> {
        val sortedList = list.toMutableList()
        rules.forEach { rule ->
            val firstIndex = sortedList.indexOf(rule.first)
            val secondIndex = sortedList.indexOf(rule.second)
            if (firstIndex > secondIndex) {
                sortedList[firstIndex] = rule.second
                sortedList[secondIndex] = rule.first
            }
        }
        return if (list == sortedList) list
        else sortItems(sortedList, rules)
    }

    private fun applyRules(pages: List<Int>, rules: List<OrderRule>): List<Int> {
        val rulesForPages = rules.filter { pages.contains(it.first) && pages.contains(it.second) }
        return sortItems(pages, rulesForPages)
    }
}
