package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Solution

abstract class AdventOfCodeDay<I : Any, out O : Any> : Solution<I, AdventOfCodeResults<O, O>> {
    override fun solve(input: I): AdventOfCodeResults<O, O> =
        AdventOfCodeResults(first(input), second(input))

    override fun process(input: List<String>): AdventOfCodeResults<O, O> {
        val parsed = parse(input)

        return AdventOfCodeResults(first(parsed), second(parsed))
    }

    abstract fun first(input: I): O

    fun processFirst(input: List<String>): O = first(parse(input))

    abstract fun second(input: I): O

    fun processSecond(input: List<String>): O = second(parse(input))
}

data class AdventOfCodeResults<out A, out B>(val first: A, val second: B) {
    override fun toString(): String = "[$first, $second]"
}

fun <T> AdventOfCodeResults<T, T>.toList(): List<T> = listOf(first, second)
