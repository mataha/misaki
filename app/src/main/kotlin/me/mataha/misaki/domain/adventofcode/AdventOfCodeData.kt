package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.domain.Solution

data class AdventOfCodeData<I : Any, out O : Any>(
    override val name: String,
    val year: Int,
    val day: Int,
    override val solution: Solution<I, O>
) : PuzzleData<I, O>
