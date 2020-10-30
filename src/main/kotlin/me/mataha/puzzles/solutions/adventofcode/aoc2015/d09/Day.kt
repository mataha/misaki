package me.mataha.puzzles.solutions.adventofcode.aoc2015.d09

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("All in a Single Night", 2015, 9)
class AllInASingleNight : AdventOfCodeDay<WeightedGraph<Location>, Int>() {
    override fun parse(input: List<String>): WeightedGraph<Location> =
        LocationGrammar.parseToEnd(input.joinToString("\n"))

    override fun first(input: WeightedGraph<Location>): Int =
        input.findRoute(Iterable<Int>::minOrNull)

    override fun second(input: WeightedGraph<Location>): Int =
        input.findRoute(Iterable<Int>::maxOrNull)
}
