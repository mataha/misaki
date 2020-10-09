package me.mataha.puzzles.adventofcode.aoc2015.day08

import me.mataha.puzzles.domain.NoOpParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.domain.adventofcode.AdventOfCode

@AdventOfCode("Matchsticks", 2015, 8)
class Matchsticks: AdventOfCodeDay<List<String>, Int>(), NoOpParser
{
    override fun first(input: List<String>): Int
            = input.fold(0, { sum, string -> sum + string.lengthLiteral() - string.lengthInMemory() })

    override fun second(input: List<String>): Int
            = input.fold(0, { sum, string -> sum + string.lengthEncoded() - string.lengthLiteral() })

    companion object
    {
        private fun String.lengthLiteral(): Int = this.length

        @JvmStatic private val ESCAPES = Regex("""\\\\|\\"|\\x[a-f0-9]{2}""")

        private fun String.lengthInMemory(): Int = this.replace(ESCAPES, "=").length - 2

        @JvmStatic private val ENCODES = Regex("""[\\"]""")

        private fun String.lengthEncoded(): Int = this.replace(ENCODES, "==").length + 2
    }
}
