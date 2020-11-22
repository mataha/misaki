package me.mataha.misaki.domain

fun interface IPuzzleSolution<Input : Any, out Output> {
    fun solve(input: Input): Output
}

abstract class PuzzleSolution<Input : Any, out Output> : IPuzzleSolution<Input, Output>, StringParser<Input> {
    /* abstract fun solve(input: Input): Output */

    open fun process(input: List<String>): Output = solve(parse(input))
}
