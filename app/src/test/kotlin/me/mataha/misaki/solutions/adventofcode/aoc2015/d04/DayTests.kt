package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import io.kotest.matchers.ints.shouldBeExactly
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

            val expected = 420
            val actual = solution.solveFirst("sfrneccg") // <1s

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `MD5 hash of given key + expected number should start with six zeroes`() {
            val solution = IdealStockingStuffer()

            val expected = 78122
            val actual = solution.solveSecond("fdglkeaa") // <1s

            actual shouldBeExactly expected
        }
    }
}
