package me.mataha.puzzles.adventofcode.aoc2015.day01

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.domain.adventofcode.AdventOfCode

@AdventOfCode("Not Quite Lisp", 2015, 1)
class NotQuiteLisp: AdventOfCodeDay<String, Int>(), OneLineParser
{
    override fun first(input: String): Int
    {
        var floor = 0

        for (char in input)
        {
            floor = ascendOrDescend(char, floor)
        }

        return floor
    }

    override fun second(input: String): Int
    {
        var floor = 0

        for ((index, char) in input.withIndex())
        {
            floor = ascendOrDescend(char, floor)

            if (floor < 0)
            {
                return index + 1
            }
        }

        return -1
    }

    companion object
    {
        private fun ascendOrDescend(char: Char, floor: Int): Int
        {
            return when (char)
            {
                '(' -> floor + 1
                ')' -> floor - 1
                else -> floor
            }
        }
    }
}
