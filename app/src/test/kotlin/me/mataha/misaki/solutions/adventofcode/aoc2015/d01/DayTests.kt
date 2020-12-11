package me.mataha.misaki.solutions.adventofcode.aoc2015.d01

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

@AdventOfCodeTest
class NotQuiteLispTests {
    @Nested
    inner class PartOne {
        @ParameterizedTest(name = "For example, at {1}F given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/01/floor.csv"], numLinesToSkip = 1)
        fun `Santa should end at the expected floor`(directions: String, expected: Int) {
            val solution = NotQuiteLisp()

            val actual = solution.solveFirst(directions)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @ParameterizedTest(name = "For example, at character position {1} given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/01/basement.csv"], numLinesToSkip = 1)
        fun `Santa should eventually enter the basement`(directions: String, expected: Int) {
            val solution = NotQuiteLisp()

            val actual = solution.solveSecond(directions)

            actual shouldBeExactly expected
        }

        @ParameterizedTest(name = "For example, given ''{0}''.")
        @EmptySource
        @ValueSource(strings = ["(((((("])
        fun `Santa should never enter the basement`(directions: String) {
            val solution = NotQuiteLisp()

            val actual = solution.solveSecond(directions)

            actual shouldBeExactly NotQuiteLisp.BASEMENT_NOT_ENTERED
        }
    }
}
