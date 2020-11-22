package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.PuzzleSolution
import me.mataha.misaki.domain.SolutionData

data class AdventOfCodeData<I : Any, out O>(
    override val name: String,
    val year: Int,
    val day: Int,
    override val solution: PuzzleSolution<I, O>
) : SolutionData<I, O>(name, solution)
