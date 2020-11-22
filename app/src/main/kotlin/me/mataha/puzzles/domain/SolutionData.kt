package me.mataha.puzzles.domain

interface ISolutionData<Input : Any, out Output> {
    val name: String
    val solution: IPuzzleSolution<Input, Output>
}

abstract class SolutionData<Input : Any, out Output>(
    override val name: String,
    override val solution: PuzzleSolution<Input, Output>
): ISolutionData<Input, Output>
