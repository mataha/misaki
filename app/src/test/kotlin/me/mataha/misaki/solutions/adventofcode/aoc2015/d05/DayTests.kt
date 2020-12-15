package me.mataha.misaki.solutions.adventofcode.aoc2015.d05

import io.kotest.assertions.withClue
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import me.mataha.misaki.domain.adventofcode.processFirst
import me.mataha.misaki.domain.adventofcode.processSecond
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@AdventOfCodeTest
class NoInternElvesForThisTests {
    @Nested
    inner class PartOne {
        @ParameterizedTest(name = "For example, ''{0}''.")
        @ValueSource(strings = ["ugknbfddgicrmopn", "aaa"])
        fun `Under old rules, given string should be nice`(string: String) {
            val solution = NoInternElvesForThis()

            val actual = solution.processFirst(string) == 0

            withClue("String '$string' should be nice.") { actual.shouldBeTrue() }
        }

        @ParameterizedTest(name = "For example, ''{0}''.")
        @ValueSource(strings = ["jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb"])
        fun `Under old rules, given string should be naughty`(string: String) {
            val solution = NoInternElvesForThis()

            val actual = solution.processFirst(string) == 1

            withClue("String '$string' should be naughty.") { actual.shouldBeFalse() }
        }
    }

    @Nested
    inner class PartTwo {
        @ParameterizedTest(name = "For example, ''{0}''.")
        @ValueSource(strings = ["qjhvhtzxzqqjkmpb", "xxyxx"])
        fun `Under new rules, given string should be nice`(string: String) {
            val solution = NoInternElvesForThis()

            val actual = solution.processSecond(string) == 1

            withClue("String '$string' should be nice.") { actual.shouldBeTrue() }
        }

        @ParameterizedTest(name = "For example, ''{0}''.")
        @ValueSource(strings = ["uurcxstgmygtbstg", "ieodomkazucvgmuy"])
        fun `Under new rules, given string should be naughty`(string: String) {
            val solution = NoInternElvesForThis()

            val actual = solution.processSecond(string) == 1

            withClue("String '$string' should be naughty.") { actual.shouldBeFalse() }
        }
    }
}
