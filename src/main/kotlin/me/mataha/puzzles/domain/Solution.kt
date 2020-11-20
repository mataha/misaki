package me.mataha.puzzles.domain

fun interface IPuzzleSolution<Input, out Output> {
    fun solve(input: Input): Output
}

abstract class PuzzleSolution<Input, out Output> : IPuzzleSolution<Input, Output>, StringParser<Input> {
    /* abstract fun solve(input: Input): Output */

    open fun process(input: List<String>): Output = solve(parse(input))
}
