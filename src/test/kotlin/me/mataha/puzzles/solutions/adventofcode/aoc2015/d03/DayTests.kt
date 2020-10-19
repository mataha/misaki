package me.mataha.puzzles.solutions.adventofcode.aoc2015.d03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PerfectlySphericalHousesTests
{
    @DisplayName("Given directions, the correct number of houses should receive at least one present")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvSource(
        ">, 2",
        "^>v<, 4",
        "^v^v^v^v^v, 2"
    )
    fun testGetHousesWithAtLeastOnePresentReceivedFromSanta(directions: String, expected: Int)
    {
        val solution = PerfectlySphericalHouses()

        val actual = solution.first(directions)

        Assertions.assertEquals(expected, actual) {
            "Given $directions, somehow, $actual house(s) received at least one present"
        }
    }

    @DisplayName("Given directions, the correct number of houses should receive at least one present")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvSource(
        "^v, 3",
        "^>v<, 3",
        "^v^v^v^v^v, 11"
    )
    fun testGetHousesWithAtLeastOnePresentReceivedFromSantaAndHisRobot(directions: String, expected: Int)
    {
        val solution = PerfectlySphericalHouses()

        val actual = solution.second(directions)

        Assertions.assertEquals(expected, actual) {
            "Given $directions, somehow, $actual house(s) received at least one present"
        }
    }
}