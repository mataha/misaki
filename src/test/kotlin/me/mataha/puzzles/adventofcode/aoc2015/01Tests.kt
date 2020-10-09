package me.mataha.puzzles.adventofcode.aoc2015

import me.mataha.puzzles.adventofcode.aoc2015.day01.NotQuiteLisp
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class NotQuiteLispTests
{
    @DisplayName("Given directions, Santa should end at the correct floor")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvFileSource(resources = ["/adventofcode/2015/01/floor.csv"], delimiter = '|', numLinesToSkip = 2)
    fun testFirst(directions: String, expected: Int)
    {
        val solution = NotQuiteLisp()

        val actual = solution.first(directions)

        assertEquals(expected, actual) {
            "Given $directions, Santa somehow ended at floor $expected"
        }
    }

    @ParameterizedTest(name = "Given \"{0}\", Santa should enter the basement at character position {1}")
    @CsvFileSource(resources = ["/adventofcode/2015/01/basement.csv"], delimiter = '|', numLinesToSkip = 2)
    fun testSecond(directions: String, expected: Int)
    {
        val solution = NotQuiteLisp()

        val actual = solution.second(directions)

        assertEquals(expected, actual) {
            "Given $directions, Santa somehow entered the basement at character position $expected"
        }
    }
}
