package me.mataha.misaki.solutions.adventofcode.aoc2020.d01

import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Report Repair", 2020, 1)
class ReportRepair : AdventOfCodeDay<List<Int>, Int>() {
    override fun parse(input: String): List<Int> =
        input.lines().map { string -> string.toInt() }

    override fun solveFirst(input: List<Int>): Int {
        val numbers = mutableSetOf<Int>()

        for (number in input) {
            val diff = YEAR - number

            if (diff in numbers) {
                return number * diff
            } else {
                numbers += number
            }
        }

        return INVALID_EXPENSE_REPORT
    }

    override fun solveSecond(input: List<Int>): Int {
        val numbers = mutableSetOf<Int>()
        val results = mutableSetOf<Result<Int>>()

        for (number in input) {
            val diff = YEAR - number

            for (result in results) {
                if (result.sum == diff) {
                    return number * result.product
                }
            }

            for (entry in numbers) {
                results += Result(number + entry, number * entry)
            }

            numbers += number
        }

        return INVALID_EXPENSE_REPORT
    }

    companion object {
        const val INVALID_EXPENSE_REPORT = -1

        private const val YEAR = 2020
    }
}

private data class Result<T : Number>(val sum: T, val product: T)
