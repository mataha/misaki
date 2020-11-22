package me.mataha.puzzles.domain.adventofcode

import me.mataha.puzzles.domain.PuzzleSolution

abstract class AdventOfCodeDay<Input : Any, out Output> : PuzzleSolution<Input, AdventOfCodeResults<Output, Output>>() {
    override fun solve(input: Input): AdventOfCodeResults<Output, Output> =
        AdventOfCodeResults(first(input), second(input))

    override fun process(input: List<String>): AdventOfCodeResults<Output, Output> {
        val parsed = parse(input)

        return AdventOfCodeResults(first(parsed), second(parsed))
    }

    abstract fun first(input: Input): Output

    fun processFirst(input: List<String>): Output = first(parse(input))

    abstract fun second(input: Input): Output

    fun processSecond(input: List<String>): Output = second(parse(input))
}

data class AdventOfCodeResults<out First, out Second>(val first: First, val second: Second) {
    override fun toString(): String = "[$first, $second]"
}

fun <Type> AdventOfCodeResults<Type, Type>.toList(): List<Type> = listOf(first, second)
