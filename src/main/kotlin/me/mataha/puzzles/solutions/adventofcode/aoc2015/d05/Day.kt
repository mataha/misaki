package me.mataha.puzzles.solutions.adventofcode.aoc2015.d05

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import me.mataha.puzzles.domain.NoOpParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.util.extensions.zipWithAfterNext

@AdventOfCode("Doesn't He Have Intern-Elves For This?", 2015, 5)
class NoInternElvesForThis: AdventOfCodeDay<List<String>, Int>(), NoOpParser
{
    override fun first(input: List<String>): Int
    {
        val forbidden = arrayOf("ab", "cd", "pq", "xy")
        val vowels = "aeiou"

        val predicates = arrayOf<(string: String) -> Boolean>(
            { string -> string.filter { vowels.contains(it) }.count() >= 3 },
            { string -> string.zipWithNext().any { it.first == it.second } },
            { string -> forbidden.none { string.contains(it) } }
        )

        return input.nice(*predicates).count()
    }

    override fun second(input: List<String>): Int
    {
        val predicates = arrayOf<(string: String) -> Boolean>(
            { string -> string.windowed(2).any { string.replaceFirst(it, "==").contains(it) } },
            { string -> string.zipWithAfterNext().any { it.first == it.second } }
        )

        return input.nice(*predicates).count()
    }

    companion object
    {
        private fun List<String>.nice(vararg predicates: (string: String) -> Boolean): List<String>
                = this.filter { string -> string.isNice(*predicates) }

        private fun String.isNice(vararg predicates: (string: String) -> Boolean): Boolean
                = runBlocking {
                    predicates
                        .asFlow()
                        .map { predicate -> predicate(this@isNice) }
                        .toList()
                        .none { condition -> !condition }
                }
    }
}