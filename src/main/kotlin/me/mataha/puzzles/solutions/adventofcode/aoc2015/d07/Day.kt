package me.mataha.puzzles.solutions.adventofcode.aoc2015.d07

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Some Assembly Required", 2015, 7)
class SomeAssemblyRequired: AdventOfCodeDay<List<Part>, Int>()
{
    override fun parse(input: List<String>): List<Part>
            = input.map { line -> CircuitGrammar.parseToEnd(line) }

    override fun first(input: List<Part>): Int
    {
        val circuit = Circuit.of(input)

        return circuit["a"].toInt()
    }

    override fun second(input: List<Part>): Int
    {
        val circuit = Circuit.of(input)

        val a = circuit["a"]
        circuit.reset()
        circuit["b"] = a

        return circuit["a"].toInt()
    }
}
