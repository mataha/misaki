package me.mataha.misaki.solutions.adventofcode.aoc2015.d08

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processPartOne
import me.mataha.misaki.domain.adventofcode.processPartTwo
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

@AdventOfCodeTest
class MatchsticksTests {
    @Nested
    inner class PartOne {
        @ParameterizedTest(name = "For example, {1} - {2} given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/08/literal-memory.csv"], numLinesToSkip = 1)
        fun `Should return length in code minus length in memory for given string`(string: String, a: Int, b: Int) {
            val solution = Matchsticks()

            val expected = a - b
            val actual = solution.processPartOne(string)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @ParameterizedTest(name = "For example, {1} - {2} given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/08/encoded-literal.csv"], numLinesToSkip = 1)
        fun `Should return length encoded minus length in code for given string`(string: String, a: Int, b: Int) {
            val solution = Matchsticks()

            val expected = a - b
            val actual = solution.processPartTwo(string)

            actual shouldBeExactly expected
        }
    }
}
