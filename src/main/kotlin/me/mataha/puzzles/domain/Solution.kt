package me.mataha.puzzles.domain

abstract class PuzzleSolution<Input, out Output>: StringParser<Input>
{
    abstract fun solve(input: Input): Output

    open fun process(input: List<String>): Output = solve(parse(input))
}
