package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import me.mataha.misaki.domain.OneLineParser
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay
import me.mataha.misaki.util.annotations.VisibleForTesting
import me.mataha.misaki.util.extensions.md5Hex

@AdventOfCode("The Ideal Stocking Stuffer", 2015, 4)
class IdealStockingStuffer : AdventOfCodeDay<String, Int>(), OneLineParser {
    override fun first(input: String): Int = md5StartingWith(input, "00000")

    override fun second(input: String): Int = md5StartingWith(input, "000000")
}

@VisibleForTesting
internal fun md5StartingWith(key: String, prefix: String, range: IntRange = 1..Int.MAX_VALUE): Int {
    require(range.first > 0) {
        "Lower bound must be a positive number (is: ${range.first})."
    }

    for (number in range) {
        val string = key + number

        if (string.md5Hex().startsWith(prefix)) {
            return number
        }
    }

    // Hash starting with that prefix was not found (given supplied range)
    return HASH_NOT_FOUND
}

private const val HASH_NOT_FOUND = -1
