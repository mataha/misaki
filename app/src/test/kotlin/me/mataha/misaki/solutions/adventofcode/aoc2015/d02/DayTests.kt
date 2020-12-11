package me.mataha.misaki.solutions.adventofcode.aoc2015.d02

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

@AdventOfCodeTest
class ThereWouldBeNoMathTests {
    @Nested
    inner class PartOne {
        @ParameterizedTest(name = "For example, {1} ft² given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/02/wrapping-paper.csv"], numLinesToSkip = 1)
        fun `Elves should order enough square feet of wrapping paper`(dimensions: String, expected: Int) {
            val day = ThereWouldBeNoMath()

            val box = day.parse(dimensions)
            val actual = day.solveFirst(box)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @ParameterizedTest(name = "For example, {1} ft given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/02/ribbon.csv"], numLinesToSkip = 1)
        fun `Elves should order enough feet of ribbon`(dimensions: String, expected: Int) {
            val day = ThereWouldBeNoMath()

            val box = day.parse(dimensions)
            val actual = day.solveSecond(box)

            actual shouldBeExactly expected
        }
    }
}
