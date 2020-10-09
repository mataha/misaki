package me.mataha.puzzles.adventofcode.aoc2015.day04

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.util.extensions.md5Hex

@AdventOfCode("The Ideal Stocking Stuffer", 2015, 4)
class IdealStockingStuffer: AdventOfCodeDay<String, Int>(), OneLineParser
{
    override fun first(input: String): Int
            = md5HashStartingWith(input, "00000")

    override fun second(input: String): Int
            = md5HashStartingWith(input, "000000")

    companion object
    {
        private fun md5HashStartingWith(key: String, start: String): Int
        {
            for (number in 1..Int.MAX_VALUE)
            {
                val string = key + number

                if (string.md5Hex().startsWith(start))
                {
                    return number
                }
            }

            return -1
        }
    }
}
