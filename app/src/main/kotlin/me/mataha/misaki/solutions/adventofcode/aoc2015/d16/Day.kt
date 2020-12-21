package me.mataha.misaki.solutions.adventofcode.aoc2015.d16

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Aunt Sue", 2015, 16)
class AuntSue : AdventOfCodeDay<List<Memoir>, Int>() {
    override fun parse(input: String): List<Memoir> = MemoirGrammar.parseToEnd(input)

    override fun solvePartOne(input: List<Memoir>): Int = match(input) { a, b, _ -> a == b }

    override fun solvePartTwo(input: List<Memoir>): Int = match(input) { a, b, key ->
        when (key) {
            "cats", "trees" -> a > b
            "pomeranians", "goldfish" -> a < b
            else -> a == b
        }
    }

    companion object {
        const val ANALYSIS_INCONCLUSIVE = -1
    }
}

private fun match(input: List<Memoir>, comparator: CompoundComparator): Int =
    input.singleOrNull { memoir -> memoir.compareTo(SAMPLE, comparator) }?.sue?.number ?: AuntSue.ANALYSIS_INCONCLUSIVE

private val SAMPLE = Memoir(
    "children" to 3,
    "cats" to 7,
    "samoyeds" to 2,
    "pomeranians" to 3,
    "akitas" to 0,
    "vizslas" to 0,
    "goldfish" to 5,
    "trees" to 3,
    "cars" to 2,
    "perfumes" to 1
)
