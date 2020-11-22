package me.mataha.misaki.domain

fun interface IPuzzleSolution<I : Any, out O> {
    fun solve(input: I): O
}

abstract class PuzzleSolution<I : Any, out O> : IPuzzleSolution<I, O>, StringParser<I> {
    /* abstract fun solve(input: Input): Output */

    open fun process(input: List<String>): O = solve(parse(input))
}
