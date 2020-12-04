package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Solution

abstract class AdventOfCodeDay<I : Any, out O : Any> : Solution<I, AdventOfCodeResults<O, O>> {
    override fun solve(input: I): AdventOfCodeResults<O, O> =
        AdventOfCodeResults(first(input), second(input))

    abstract fun first(input: I): O

    abstract fun second(input: I): O
}

data class AdventOfCodeResults<out A, out B>(val first: A, val second: B) {
    override fun toString(): String = "[$first, $second]"
}

fun <T> AdventOfCodeResults<T, T>.toList(): List<T> = listOf(first, second)
