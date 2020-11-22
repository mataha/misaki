package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.PuzzleSolution
import me.mataha.misaki.domain.SolutionData

data class AdventOfCodeData<Input : Any, out Output>(
    override val name: String,
    val year: Int,
    val day: Int,
    override val solution: PuzzleSolution<Input, Output>
) : SolutionData<Input, Output>(name, solution)
