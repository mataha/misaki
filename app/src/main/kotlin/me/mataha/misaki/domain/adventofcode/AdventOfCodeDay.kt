package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Solution

abstract class AdventOfCodeDay<I : Any, out O : Any> : Solution<I, AdventOfCodeResult<O, O>> {
    override fun solve(input: I): AdventOfCodeResult<O, O> =
        AdventOfCodeResult(solveFirst(input), solveSecond(input))

    abstract fun solveFirst(input: I): O

    abstract fun solveSecond(input: I): O
}

fun <I : Any, O : Any> AdventOfCodeDay<I, O>.processFirst(input: String): O = solveFirst(parse(input))

fun <I : Any, O : Any> AdventOfCodeDay<I, O>.processSecond(input: String): O = solveSecond(parse(input))

data class AdventOfCodeResult<out A, out B>(val first: A, val second: B) {
    override fun toString(): String = "[$first, $second]"
}

fun <T> AdventOfCodeResult<T, T>.toList(): List<T> = listOf(first, second)
