package me.mataha.puzzles.solutions.adventofcode.aoc2015.d01

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Not Quite Lisp", 2015, 1)
class NotQuiteLisp : AdventOfCodeDay<String, Int>(), OneLineParser {
    override fun first(input: String): Int {
        var floor = 0

        for (char in input) {
            floor = changeFloor(char, floor)
        }

        return floor
    }

    override fun second(input: String): Int {
        var floor = 0

        for ((index, char) in input.withIndex()) {
            floor = changeFloor(char, floor)

            if (floor < 0) {
                return index + 1
            }
        }

        // Santa did never enter the basement
        return -1
    }
}

private fun changeFloor(char: Char, floor: Int): Int {
    return when (char) {
        '(' -> floor + 1
        ')' -> floor - 1
        else -> floor
    }
}
