package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Solution

abstract class AdventOfCodeDay<I : Any, out O : Any> : Solution<I, AdventOfCodeResult<O, O>> {
    override fun solve(input: I): AdventOfCodeResult<O, O> =
        AdventOfCodeResult(solvePartOne(input), solvePartTwo(input))

    abstract fun solvePartOne(input: I): O

    abstract fun solvePartTwo(input: I): O
}

fun <I : Any, O : Any> AdventOfCodeDay<I, O>.processPartOne(input: String): O = solvePartOne(parse(input))

fun <I : Any, O : Any> AdventOfCodeDay<I, O>.processPartTwo(input: String): O = solvePartTwo(parse(input))

data class AdventOfCodeResult<out A, out B>(val partOne: A, val partTwo: B) {
    override fun toString(): String = "[$partOne, $partTwo]"
}

fun <T> AdventOfCodeResult<T, T>.toList(): List<T> = listOf(partOne, partTwo)
