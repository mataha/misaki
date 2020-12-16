package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

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
class IdealStockingStufferTests {
    @Nested
    inner class PartOne {
        @Test
        fun `MD5 hash of given key + expected number should start with five zeroes`() {
            val solution = IdealStockingStuffer()

            val expected = 15
            val actual = solution.processFirst("taxcnqsl")

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `MD5 hash of given key + expected number should start with six zeroes`() {
            val solution = IdealStockingStuffer()

            val expected = 50
            val actual = solution.processSecond("eassxskp")

            actual shouldBeExactly expected
        }
    }
}
