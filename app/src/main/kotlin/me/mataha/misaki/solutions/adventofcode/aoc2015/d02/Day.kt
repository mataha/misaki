package me.mataha.misaki.solutions.adventofcode.aoc2015.d02

import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.util.annotations.VisibleForTesting

/** See [https://adventofcode.com/2015/day/2]. */
@AdventOfCode("I Was Told There Would Be No Math", 2015, 2)
class ThereWouldBeNoMath : AdventOfCodeDay<List<Box>, Int>() {
    override fun parse(input: String): List<Box> =
        input
            .lines()
            .map { line -> line.split(PARSE_DELIMITER).map { it.toInt() } }
            .map { dimensions -> Box.create(dimensions) }

    override fun solveFirst(input: List<Box>): Int =
        input.sumBy { box -> getSquareFeetOfWrappingPaper(box) }

    override fun solveSecond(input: List<Box>): Int =
        input.sumBy { box -> getFeetOfRibbon(box) }

    private companion object {
        private const val PARSE_DELIMITER = 'x'
    }
}

@VisibleForTesting
internal fun getSquareFeetOfWrappingPaper(box: Box): Int =
    box.smallestSideArea + box.surfaceArea

@VisibleForTesting
internal fun getFeetOfRibbon(box: Box): Int =
    box.smallestSidePerimeter + box.volume
