package me.mataha.puzzles.solutions.adventofcode.aoc2015.d05

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import me.mataha.puzzles.domain.NoOpParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.util.annotations.VisibleForTesting
import me.mataha.puzzles.util.extensions.zipWithAfterNext

@AdventOfCode("Doesn't He Have Intern-Elves For This?", 2015, 5)
class NoInternElvesForThis: AdventOfCodeDay<List<String>, Int>(), NoOpParser
{
    override fun first(input: List<String>): Int = input.count(String::isNiceBefore)

    override fun second(input: List<String>): Int = input.count(String::isNiceAfter)
}

private fun String.isNice(vararg predicates: (string: String) -> Boolean): Boolean
        = runBlocking {
            predicates
                .asFlow()
                .map { predicate -> predicate(this@isNice) }
                .toList()
                .none { condition -> !condition }
        }

private const val VOWELS = "aeiou"

@VisibleForTesting
internal fun String.isNiceBefore(): Boolean
{
    val forbidden = arrayOf("ab", "cd", "pq", "xy")

    val predicates = arrayOf<(string: String) -> Boolean>(
        { string -> string.count { char -> char in VOWELS } >= 3 },
        { string -> string.zipWithNext().any { pair -> pair.first == pair.second } },
        { string -> forbidden.none { letters -> letters in string } }
    )

    return this.isNice(*predicates)
}

@VisibleForTesting
internal fun String.isNiceAfter(): Boolean
{
    val predicates = arrayOf<(string: String) -> Boolean>(
        { string -> string.windowed(2).any { window -> window in string.replaceFirst(window, "  ") } },
        { string -> string.zipWithAfterNext().any { pair -> pair.first == pair.second } }
    )

    return this.isNice(*predicates)
}
