package me.mataha.misaki.solutions.adventofcode.aoc2020.d01

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processFirst
import me.mataha.misaki.domain.adventofcode.processSecond
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@AdventOfCodeTest
class ReportRepairTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Should find two entries in an expense report that sum to 2020 and multiply them`() {
            val solution = ReportRepair()

            val input = """
                1721
                979
                366
                299
                675
                1456
            """.trimIndent()

            val expected = 514579
            val actual = solution.processFirst(input)

            actual shouldBeExactly expected
        }

        @Test
        fun `Should fail to find two entries in an expense report that sum to 2020`() {
            val solution = ReportRepair()

            val input = "42"

            val actual = solution.processFirst(input)

            actual shouldBeExactly ReportRepair.INVALID_EXPENSE_REPORT
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Should find three entries in an expense report that sum to 2020 and multiply them`() {
            val solution = ReportRepair()

            val input = """
                1721
                979
                366
                299
                675
                1456
            """.trimIndent()

            val expected = 241861950
            val actual = solution.processSecond(input)

            actual shouldBeExactly expected
        }

        @Test
        fun `Should fail to find three entries in an expense report that sum to 2020`() {
            val solution = ReportRepair()

            val input = "42"

            val actual = solution.processSecond(input)

            actual shouldBeExactly ReportRepair.INVALID_EXPENSE_REPORT
        }
    }
}
