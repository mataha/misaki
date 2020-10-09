package me.mataha.puzzles.adventofcode.aoc2015.day02

import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.domain.adventofcode.AdventOfCode

@AdventOfCode("I Was Told There Would Be No Math", 2015, 2)
class ThereWouldBeNoMath: AdventOfCodeDay<List<Box>, Int>()
{
    override fun parse(input: List<String>): List<Box>
            = input
                .map { line -> line.split(PARSE_DELIMITER).map { it.toInt() } }
                .map { dimensions -> Box.create(dimensions) }

    override fun first(input: List<Box>): Int
            = input.fold(0, { sum, box -> sum + box.surfaceArea + box.smallestSideArea })

    override fun second(input: List<Box>): Int
            = input.fold(0, { sum, box -> sum + box.smallestSidePerimeter + box.volume })

    companion object
    {
        private const val PARSE_DELIMITER = 'x'
    }
}
