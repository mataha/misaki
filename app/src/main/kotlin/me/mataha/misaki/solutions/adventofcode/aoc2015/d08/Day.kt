package me.mataha.misaki.solutions.adventofcode.aoc2015.d08

import me.mataha.misaki.domain.LineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.*
import me.mataha.misaki.util.annotations.VisibleForTesting

@AdventOfCode("Matchsticks", 2015, 8)
class Matchsticks : AdventOfCodeDay<List<String>, Int>(), LineParser {
    override fun first(input: List<String>): Int =
        input.sumBy { string -> string.length(LITERAL) - string.length(IN_MEMORY) }

    override fun second(input: List<String>): Int =
        input.sumBy { string -> string.length(ENCODED) - string.length(LITERAL) }
}

@VisibleForTesting
internal enum class Representation { LITERAL, IN_MEMORY, ENCODED }

@VisibleForTesting
internal fun String.length(representation: Representation): Int {
    return when (representation) {
        LITERAL -> this.length
        IN_MEMORY -> this.replace(ESCAPES, " ").length - 2
        ENCODED -> this.replace(ENCODES, "  ").length + 2
    }
}

private val ESCAPES = Regex("""\\\\|\\"|\\x[a-f0-9]{2}""")
private val ENCODES = Regex("""[\\"]""")
