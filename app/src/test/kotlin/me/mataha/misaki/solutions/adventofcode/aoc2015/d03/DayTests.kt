package me.mataha.misaki.solutions.adventofcode.aoc2015.d03

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

@AdventOfCodeTest
class PerfectlySphericalHousesTests {
    @Nested
    inner class PartOne {
        @ParameterizedTest(name = "For example, {1} house(s) given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/03/santa.csv"], numLinesToSkip = 1)
        fun `Enough houses should receive at least one present from Santa`(moves: String, expected: Int) {
            val solution = PerfectlySphericalHouses()

            val actual = solution.solveFirst(moves)

            actual shouldBeExactly expected
        }
    }
    @Nested
    inner class PartTwo {
        @ParameterizedTest(name = "For example, {1} house(s) given ''{0}''.")
        @CsvFileSource(resources = ["/solutions/adventofcode/2015/03/santa+robot.csv"], numLinesToSkip = 1)
        fun `Enough houses should receive at least one present from Santa and Robot`(moves: String, expected: Int) {
            val solution = PerfectlySphericalHouses()

            val actual = solution.solveSecond(moves)

            actual shouldBeExactly expected
        }
    }
}
