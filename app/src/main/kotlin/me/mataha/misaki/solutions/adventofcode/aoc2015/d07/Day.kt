package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

/** See [https://adventofcode.com/2015/day/7]. */
@AdventOfCode("Some Assembly Required", 2015, 7)
class SomeAssemblyRequired : AdventOfCodeDay<List<Part>, Int>() {
    override fun parse(input: String): List<Part> =
        CircuitGrammar.parseToEnd(input)

    override fun solvePartOne(input: List<Part>): Int {
        val circuit = Circuit.of(input)

        return circuit["a"].toInt()
    }

    override fun solvePartTwo(input: List<Part>): Int {
        val circuit = Circuit.of(input)

        val a = circuit["a"]
        circuit.reset()
        circuit["b"] = a

        return circuit["a"].toInt()
    }
}
