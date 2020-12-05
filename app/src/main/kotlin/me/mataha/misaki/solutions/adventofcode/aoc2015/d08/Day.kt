package me.mataha.misaki.solutions.adventofcode.aoc2015.d08

import me.mataha.misaki.domain.LineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.*

/** See [https://adventofcode.com/2015/day/8]. */
@AdventOfCode("Matchsticks", 2015, 8)
class Matchsticks : AdventOfCodeDay<List<String>, Int>(), LineParser {
    override fun solveFirst(input: List<String>): Int =
        input.sumBy { string -> string.length(LITERAL) - string.length(IN_MEMORY) }

    override fun solveSecond(input: List<String>): Int =
        input.sumBy { string -> string.length(ENCODED) - string.length(LITERAL) }
}
