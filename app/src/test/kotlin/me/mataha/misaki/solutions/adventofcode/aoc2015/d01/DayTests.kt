package me.mataha.misaki.solutions.adventofcode.aoc2015.d01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class NotQuiteLispTests {
    @DisplayName("Given directions, Santa should end at the correct floor")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvFileSource(resources = ["/solutions/adventofcode/2015/01/floor.csv"], numLinesToSkip = 1)
    fun testFirst(directions: String, expected: Int) {
        val solution = NotQuiteLisp()

        val actual = solution.solveFirst(directions)

        assertEquals(expected, actual) {
            "Given $directions, Santa somehow ended at floor $expected"
        }
    }

    @ParameterizedTest(name = "Given \"{0}\", Santa should enter the basement at character position {1}")
    @CsvFileSource(resources = ["/solutions/adventofcode/2015/01/basement.csv"], numLinesToSkip = 1)
    fun testSecond(directions: String, expected: Int) {
        val solution = NotQuiteLisp()

        val actual = solution.solveSecond(directions)

        assertEquals(expected, actual) {
            "Given $directions, Santa somehow entered the basement at character position $expected"
        }
    }
}
