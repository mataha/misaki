package me.mataha.puzzles.adventofcode.aoc2015.day10

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Elves Look, Elves Say", 2015, 10)
class ElvesLookElvesSay: AdventOfCodeDay<String, Int>(), OneLineParser
{
    override fun first(input: String): Int = input.lookAndSay(40).length

    override fun second(input: String): Int = input.lookAndSay(50).length

    companion object
    {
        internal fun String.lookAndSay(steps: Int = 1): String
        {
            require(steps >= 1)
                { "Number of steps has to be positive (is: $steps)." }

            var string = this

            for (unused in 1..steps)
            {
                string = string.phonetic()
            }

            return string
        }

        private fun String.phonetic(): String
                = REGEX.findAll(this).fold(StringBuilder(), { builder, match ->
                    builder.append(match.value.length).append(match.value.first())
                }).toString()

        @JvmStatic private val REGEX = Regex("""(.)\1*""")
    }
}
