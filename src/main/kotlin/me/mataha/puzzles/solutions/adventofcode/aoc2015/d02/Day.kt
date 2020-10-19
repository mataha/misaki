package me.mataha.puzzles.solutions.adventofcode.aoc2015.d02

import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("I Was Told There Would Be No Math", 2015, 2)
class ThereWouldBeNoMath: AdventOfCodeDay<List<Box>, Int>()
{
    override fun parse(input: List<String>): List<Box>
            = input
                .map { line -> line.split(PARSE_DELIMITER).map { it.toInt() } }
                .map { dimensions -> Box.create(dimensions) }

    override fun first(input: List<Box>): Int
            = input.sumBy { box -> box.surfaceArea + box.smallestSideArea }

    override fun second(input: List<Box>): Int
            = input.sumBy { box -> box.smallestSidePerimeter + box.volume }

    companion object
    {
        private const val PARSE_DELIMITER = 'x'
    }
}