package me.mataha.misaki.solutions.adventofcode.aoc2015.d08

import me.mataha.misaki.solutions.adventofcode.aoc2015.d08.Representation.*

internal fun String.length(representation: Representation): Int {
    return when (representation) {
        LITERAL -> this.length
        IN_MEMORY -> this.replace(ESCAPES, " ").length - 2
        ENCODED -> this.replace(ENCODES, "  ").length + 2
    }
}

private val ESCAPES = Regex("""\\\\|\\"|\\x[a-f0-9]{2}""")
private val ENCODES = Regex("""[\\"]""")
