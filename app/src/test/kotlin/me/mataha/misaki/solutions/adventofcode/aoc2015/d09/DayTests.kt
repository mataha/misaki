package me.mataha.misaki.solutions.adventofcode.aoc2015.d09

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processFirst
import me.mataha.misaki.domain.adventofcode.processSecond
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@AdventOfCodeTest
class AllInASingleNightTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Should compute the shortest route between all given locations`() {
            val solution = AllInASingleNight()

            val input = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
            """.trimIndent()

            val expected = 605
            val actual = solution.processFirst(input)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Should compute the longest route between all given locations`() {
            val solution = AllInASingleNight()

            val input = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
            """.trimIndent()

            val expected = 982
            val actual = solution.processSecond(input)

            actual shouldBeExactly expected
        }
    }
}
