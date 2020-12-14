package me.mataha.misaki.solutions.adventofcode.aoc2015.d05

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import me.mataha.misaki.domain.LineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.util.extensions.zipAdjacent

/** See [https://adventofcode.com/2015/day/5]. */
@AdventOfCode("Doesn't He Have Intern-Elves For This?", 2015, 5)
class NoInternElvesForThis : AdventOfCodeDay<List<String>, Int>(), LineParser {
    override fun solveFirst(input: List<String>): Int = input.count(String::isNiceBefore)

    override fun solveSecond(input: List<String>): Int = input.count(String::isNiceAfter)
}

private const val VOWELS = "aeiou"

private fun String.isNiceBefore(): Boolean {
    val forbidden = arrayOf("ab", "cd", "pq", "xy")

    val predicates = arrayOf<(string: String) -> Boolean>(
        { string -> string.count { char -> char in VOWELS } >= 3 },
        { string -> string.zipWithNext().any { pair -> pair.first == pair.second } },
        { string -> forbidden.none { letters -> letters in string } }
    )

    return this.isNice(*predicates)
}

private fun String.isNiceAfter(): Boolean {
    val predicates = arrayOf<(string: String) -> Boolean>(
        { string -> string.windowed(2).any { window -> window in string.replaceFirst(window, "  ") } },
        { string -> string.zipAdjacent().any { pair -> pair.first == pair.second } }
    )

    return this.isNice(*predicates)
}

private fun String.isNice(vararg predicates: (string: String) -> Boolean): Boolean = runBlocking {
    predicates
        .asFlow()
        .map { predicate -> predicate(this@isNice) }
        .toList()
        .none { condition -> !condition }
}
