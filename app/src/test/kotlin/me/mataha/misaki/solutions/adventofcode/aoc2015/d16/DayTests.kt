package me.mataha.misaki.solutions.adventofcode.aoc2015.d16

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processPartOne
import me.mataha.misaki.domain.adventofcode.processPartTwo
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@AdventOfCodeTest
class AuntSueTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Should find the number of Aunt Sue that sent the gift (comparing exact values)`() {
            val solution = AuntSue()

            val input = """
                Sue 1: akitas: 2, samoyeds: 1, perfumes: 8
                Sue 2: children: 3, goldfish: 5, cats: 7
                Sue 3: trees: 1, vizslas: 1, cars: 2
                Sue 4: perfumes: 1, pomeranians: 1, trees: 4
            """.trimIndent()

            val expected = 2
            val actual = solution.processPartOne(input)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Should find the number of Aunt Sue that sent the gift (comparing ranges)`() {
            val solution = AuntSue()

            val input = """
                Sue 1: akitas: 2, samoyeds: 1, perfumes: 8
                Sue 2: children: 3, goldfish: 5, cats: 7
                Sue 3: trees: 1, vizslas: 1, cars: 2
                Sue 4: perfumes: 1, pomeranians: 1, trees: 4
            """.trimIndent()

            val expected = 4
            val actual = solution.processPartTwo(input)

            actual shouldBeExactly expected
        }
    }
}
