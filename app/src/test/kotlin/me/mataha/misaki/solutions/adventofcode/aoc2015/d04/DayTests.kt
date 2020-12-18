package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processPartOne
import me.mataha.misaki.domain.adventofcode.processPartTwo
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@AdventOfCodeTest
@Execution(CONCURRENT)
class IdealStockingStufferTests {
    @Nested
    inner class PartOne {
        @Test
        fun `MD5 hash of given key + expected number should start with five zeroes`() {
            val solution = IdealStockingStuffer()

            val expected = 15
            val actual = solution.processPartOne("taxcnqsl")

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `MD5 hash of given key + expected number should start with six zeroes`() {
            val solution = IdealStockingStuffer()

            val expected = 50
            val actual = solution.processPartTwo("eassxskp")

            actual shouldBeExactly expected
        }
    }
}
