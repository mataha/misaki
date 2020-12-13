package me.mataha.misaki.solutions.adventofcode.aoc2015.d10

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processFirst
import me.mataha.misaki.domain.adventofcode.processSecond
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@AdventOfCodeTest
@Execution(CONCURRENT)
class ElvesLookElvesSayTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Should compute the length of look-and-say applied 40 times to given string`() {
            val solution = ElvesLookElvesSay()

            val input = "1"

            val expected = 82350
            val actual = solution.processFirst(input)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Should compute the length of look-and-say applied 50 times to given string`() {
            val solution = ElvesLookElvesSay()

            val input = "1"

            val expected = 1166642
            val actual = solution.processSecond(input)

            actual shouldBeExactly expected
        }
    }
}
