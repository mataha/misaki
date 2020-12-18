package me.mataha.misaki.solutions.adventofcode.aoc2015.d10

import me.mataha.misaki.domain.NoOpParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

/** See [https://adventofcode.com/2015/day/10]. */
@AdventOfCode("Elves Look, Elves Say", 2015, 10)
class ElvesLookElvesSay : AdventOfCodeDay<String, Int>(), NoOpParser {
    override fun solvePartOne(input: String): Int = lookAndSay(input, 40).length

    override fun solvePartTwo(input: String): Int = lookAndSay(input, 50).length
}

private fun lookAndSay(string: String, steps: Int = 1): String {
    require(steps >= 1) {
        "Number of steps has to be positive (is: $steps)"
    }

    var str = string

    repeat(steps) { str = str.phonetic() }

    return str
}

private val REGEX = Regex("""(.)\1*""")

private fun String.phonetic(): String =
    REGEX.findAll(this).fold(StringBuilder(), { builder, match ->
        builder.append(match.value.length).append(match.value.first())
    }).toString()
