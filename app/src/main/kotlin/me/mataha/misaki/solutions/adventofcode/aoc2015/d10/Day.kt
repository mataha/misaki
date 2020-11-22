package me.mataha.misaki.solutions.adventofcode.aoc2015.d10

import me.mataha.misaki.domain.OneLineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.util.annotations.VisibleForTesting

@AdventOfCode("Elves Look, Elves Say", 2015, 10)
class ElvesLookElvesSay : AdventOfCodeDay<String, Int>(), OneLineParser {
    override fun first(input: String): Int = lookAndSay(input, 40).length

    override fun second(input: String): Int = lookAndSay(input, 50).length
}

@VisibleForTesting
internal fun lookAndSay(string: String, steps: Int = 1): String {
    require(steps >= 1) {
        "Number of steps has to be positive (is: $steps)."
    }

    var str = string

    (1..steps).forEach { _ -> str = str.phonetic() }

    return str
}

private val REGEX = Regex("""(.)\1*""")

private fun String.phonetic(): String =
    REGEX.findAll(this).fold(StringBuilder(), { builder, match ->
        builder.append(match.value.length).append(match.value.first())
    }).toString()
