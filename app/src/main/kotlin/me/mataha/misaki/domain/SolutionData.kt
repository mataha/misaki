package me.mataha.misaki.domain

interface ISolutionData<I : Any, out O> {
    val name: String
    val solution: IPuzzleSolution<I, O>
}

abstract class SolutionData<I : Any, out O>(
    override val name: String,
    override val solution: PuzzleSolution<I, O>
): ISolutionData<I, O>
