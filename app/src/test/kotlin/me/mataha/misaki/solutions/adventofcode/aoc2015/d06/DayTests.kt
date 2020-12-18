package me.mataha.misaki.solutions.adventofcode.aoc2015.d06

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processPartOne
import me.mataha.misaki.domain.adventofcode.processPartTwo
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@AdventOfCodeTest
class ProbablyAFireHazardTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Given (mistranslated) instructions, the expected number of lights should be lit`() {
            val solution = ProbablyAFireHazard()

            val input = """
                turn on 0,0 through 999,999
                toggle 0,0 through 999,0
                turn off 499,499 through 500,500
            """.trimIndent()

            val expected = 1_000_000 - 1_000 - 4
            val actual = solution.processPartOne(input)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Given (properly translated) instructions, the expected number of lights should be lit`() {
            val solution = ProbablyAFireHazard()

            val input = """
                turn on 0,0 through 0,0
                toggle 0,0 through 999,999
                turn off 499,499 through 500,500
            """.trimIndent()

            val expected = 1 + 2_000_000 - 4
            val actual = solution.processPartTwo(input)

            actual shouldBeExactly expected
        }
    }
}
