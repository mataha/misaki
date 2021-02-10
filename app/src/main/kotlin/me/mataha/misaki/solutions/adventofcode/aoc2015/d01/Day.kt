package me.mataha.misaki.solutions.adventofcode.aoc2015.d01

import me.mataha.misaki.domain.NoOpParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.util.functional.take

/** See the puzzle's full description [here](https://adventofcode.com/2015/day/1). */
@AdventOfCode("Not Quite Lisp", 2015, 1)
class NotQuiteLisp : AdventOfCodeDay<String, Int>(), NoOpParser {
    override fun solvePartOne(input: String): Int =
        input.fold(GROUND_FLOOR) { accumulator, direction ->
            accumulator + go(direction)
        }

    override fun solvePartTwo(input: String): Int =
        input.foldIndexed(GROUND_FLOOR) { index, accumulator, direction ->
            val floor = accumulator + go(direction)
            if (floor < 0) return index + 1
            floor
        } take BASEMENT_NOT_ENTERED

    companion object {
        const val BASEMENT_NOT_ENTERED = -1

        private const val GROUND_FLOOR = 0
    }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun go(direction: Char): Int = when (direction) {
    '(' -> +1
    ')' -> -1
    else -> 0
}
