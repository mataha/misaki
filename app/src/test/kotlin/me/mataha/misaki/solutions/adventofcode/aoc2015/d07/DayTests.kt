package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import io.kotest.matchers.ints.shouldBeExactly
import me.mataha.misaki.domain.adventofcode.processPartOne
import me.mataha.misaki.domain.adventofcode.processPartTwo
import me.mataha.misaki.solutions.adventofcode.AdventOfCodeTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@AdventOfCodeTest
class SomeAssemblyRequiredTests {
    @Nested
    inner class PartOne {
        @Test
        fun `Wire 'a' should be provided with the expected signal`() {
            val solution = SomeAssemblyRequired()

            // See: solutions/adventofcode/2015/07/circuit.txt
            val input = """
                m AND d -> n
                i AND m -> h
                j AND h -> f
                g OR k -> c
                333 -> d
                171 -> b
                b -> i
                NOT k -> j
                i RSHIFT 1 -> k
                n AND f -> e
                NOT b -> m
                l LSHIFT 2 -> g
                e OR c -> a
                420 -> l
            """.trimIndent()

            val expected = 1749
            val actual = solution.processPartOne(input)

            actual shouldBeExactly expected
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun `Wire 'a' should be provided with the expected signal (after override and reset)`() {
            val solution = SomeAssemblyRequired()

            // See: solutions/adventofcode/2015/07/circuit.txt
            val input = """
                m AND d -> n
                i AND m -> h
                j AND h -> f
                g OR k -> c
                333 -> d
                171 -> b
                b -> i
                NOT k -> j
                i RSHIFT 1 -> k
                n AND f -> e
                NOT b -> m
                l LSHIFT 2 -> g
                e OR c -> a
                420 -> l
            """.trimIndent()

            val expected = 2042
            val actual = solution.processPartTwo(input)

            actual shouldBeExactly expected
        }
    }
}
