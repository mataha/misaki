package me.mataha.puzzles.solutions.adventofcode.aoc2015.d03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class PerfectlySphericalHousesTests {
    @DisplayName("Given directions, the correct number of houses should receive at least one present")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvFileSource(resources = ["/solutions/adventofcode/2015/03/santa.csv"], numLinesToSkip = 1)
    fun testGetHousesWithAtLeastOnePresentReceivedFromSanta(directions: String, expected: Int) {
        val solution = PerfectlySphericalHouses()

        val actual = solution.first(directions)

        Assertions.assertEquals(expected, actual) {
            "Given $directions, somehow, $actual house(s) received at least one present"
        }
    }

    @DisplayName("Given directions, the correct number of houses should receive at least one present")
    @ParameterizedTest(name = "{0} => {1}")
    @CsvFileSource(resources = ["/solutions/adventofcode/2015/03/santa-robot.csv"], numLinesToSkip = 1)
    fun testGetHousesWithAtLeastOnePresentReceivedFromSantaAndHisRobot(directions: String, expected: Int) {
        val solution = PerfectlySphericalHouses()

        val actual = solution.second(directions)

        Assertions.assertEquals(expected, actual) {
            "Given $directions, somehow, $actual house(s) received at least one present"
        }
    }
}