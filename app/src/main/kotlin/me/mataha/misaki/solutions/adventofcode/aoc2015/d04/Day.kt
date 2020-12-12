package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import me.mataha.misaki.domain.NoOpParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.solutions.adventofcode.aoc2015.d04.IdealStockingStuffer.Companion.HASH_NOT_FOUND
import me.mataha.misaki.util.annotations.VisibleForTesting
import me.mataha.misaki.util.extensions.md5Hex

/** See [https://adventofcode.com/2015/day/4]. */
@AdventOfCode("The Ideal Stocking Stuffer", 2015, 4)
class IdealStockingStuffer : AdventOfCodeDay<String, Int>(), NoOpParser {
    override fun solveFirst(input: String): Int = findLowestMd5StartingWith(input, "0".repeat(5))

    override fun solveSecond(input: String): Int = findLowestMd5StartingWith(input, "0".repeat(6))

    companion object {
        const val HASH_NOT_FOUND = -1
    }
}

@VisibleForTesting
internal fun findLowestMd5StartingWith(key: String, prefix: String): Int {
    repeat(Int.MAX_VALUE - 1) {
        val number = it + 1
        val string = key + number

        if (string.md5Hex().startsWith(prefix)) {
            return number
        }
    }

    // Hash starting with that prefix was not found
    return HASH_NOT_FOUND
}
