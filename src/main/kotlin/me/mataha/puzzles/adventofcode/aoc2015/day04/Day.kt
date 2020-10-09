package me.mataha.puzzles.adventofcode.aoc2015.day04

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@AdventOfCode("The Ideal Stocking Stuffer", 2015, 4)
class IdealStockingStuffer: AdventOfCodeDay<String, Int>(), OneLineParser
{
    override fun first(input: String): Int = findMD5HashStartingWith(input, "00000")

    override fun second(input: String): Int = findMD5HashStartingWith(input, "000000")

    companion object
    {
        private fun findMD5HashStartingWith(key: String, startingWith: String): Int
        {
            var number = 1
            val instance = MessageDigest.getMD5Instance()

            while (true)
            {
                val string = key + number
                val hash = instance.digest(string.toByteArray(StandardCharsets.UTF_8))

                if (hash.joinToString("") { "%02x".format(it) }.startsWith(startingWith))
                {
                    return number
                }

                number++
            }
        }
    }
}

private const val MD5 = "MD5"

// This looked so insanely wrong and hacky that I just had to do it.
// """Static""" extension on a nullable receiver! Have to use a FQCN though...
@Suppress("unused")
private fun MessageDigest?.getMD5Instance() = java.security.MessageDigest.getInstance(MD5)

// Hide class behind a nullable type - allows me to invoke above function normally
// (i.e. 'MessageDigest.getMD5Instance()').
private val MessageDigest: MessageDigest? = null
