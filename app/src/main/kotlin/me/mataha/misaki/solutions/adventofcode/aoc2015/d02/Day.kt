package me.mataha.misaki.solutions.adventofcode.aoc2015.d02

import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

/** See the puzzle's full description [here](https://adventofcode.com/2015/day/2). */
@AdventOfCode("I Was Told There Would Be No Math", 2015, 2)
class ThereWouldBeNoMath : AdventOfCodeDay<List<Box>, Int>() {
    override fun parse(input: String): List<Box> =
        input
            .lines()
            .map { line -> line.split(DELIMITER).map { dimension -> dimension.toInt() } }
            .map { dimensions -> Box.create(dimensions) }

    override fun solvePartOne(input: List<Box>): Int =
        input.sumOf { box -> getSquareFeetOfWrappingPaper(box) }

    override fun solvePartTwo(input: List<Box>): Int =
        input.sumOf { box -> getFeetOfRibbon(box) }

    private companion object {
        private const val DELIMITER = 'x'
    }
}

private fun getSquareFeetOfWrappingPaper(box: Box): Int =
    box.smallestSideArea + box.surfaceArea

private fun getFeetOfRibbon(box: Box): Int =
    box.smallestSidePerimeter + box.volume
