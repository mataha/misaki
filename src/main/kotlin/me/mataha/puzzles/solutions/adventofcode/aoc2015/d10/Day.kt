package me.mataha.puzzles.solutions.adventofcode.aoc2015.d10

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.util.annotations.VisibleForTesting

@AdventOfCode("Elves Look, Elves Say", 2015, 10)
class ElvesLookElvesSay : AdventOfCodeDay<String, Int>(), OneLineParser {
    override fun first(input: String): Int = input.lookAndSay(40).length

    override fun second(input: String): Int = input.lookAndSay(50).length
}

@VisibleForTesting
internal fun String.lookAndSay(steps: Int = 1): String {
    require(steps >= 1) {
        "Number of steps has to be positive (is: $steps)."
    }

    var string = this

    for (_unused in 1..steps) {
        string = string.phonetic()
    }

    return string
}

private val REGEX = Regex("""(.)\1*""")

private fun String.phonetic(): String =
    REGEX.findAll(this).fold(StringBuilder(), { builder, match ->
        builder.append(match.value.length).append(match.value.first())
    }).toString()
