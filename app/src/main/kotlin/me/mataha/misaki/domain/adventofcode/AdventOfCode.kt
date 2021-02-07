package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Puzzle
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Puzzle(origin = AdventOfCode.NAME, handles = ["aoc", "adventofcode"], factory = AdventOfCodeDataFactory::class)
annotation class AdventOfCode(val task: String, val year: Int, val day: Int) {
    companion object {
        const val NAME = "Advent of Code"
    }
}
