package me.mataha.misaki.solutions.adventofcode.aoc2015.d08

import me.mataha.misaki.domain.LineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.ENCODED
import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.IN_CODE
import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.IN_MEMORY

/** See the puzzle's full description [here](https://adventofcode.com/2015/day/8). */
@AdventOfCode("Matchsticks", 2015, 8)
class Matchsticks : AdventOfCodeDay<List<String>, Int>(), LineParser {
    override fun solvePartOne(input: List<String>): Int =
        input.sumOf { string -> string.length(IN_CODE) - string.length(IN_MEMORY) }

    override fun solvePartTwo(input: List<String>): Int =
        input.sumOf { string -> string.length(ENCODED) - string.length(IN_CODE) }
}
