package me.mataha.puzzles.solutions.adventofcode.aoc2015.d02

import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.util.annotations.VisibleForTesting

@AdventOfCode("I Was Told There Would Be No Math", 2015, 2)
class ThereWouldBeNoMath: AdventOfCodeDay<List<Box>, Int>()
{
    override fun parse(input: List<String>): List<Box>
            = input
                .map { line -> line.split(PARSE_DELIMITER).map { it.toInt() } }
                .map { dimensions -> Box.create(dimensions) }

    override fun first(input: List<Box>): Int
            = input.sumBy { box -> getSquareFeetOfWrappingPaper(box) }

    override fun second(input: List<Box>): Int
            = input.sumBy { box -> getFeetOfRibbon(box) }

    private companion object
    {
        private const val PARSE_DELIMITER = 'x'
    }
}

@VisibleForTesting
internal fun getSquareFeetOfWrappingPaper(box: Box): Int
        = box.surfaceArea + box.smallestSideArea

@VisibleForTesting
internal fun getFeetOfRibbon(box: Box): Int
        = box.smallestSidePerimeter + box.volume
