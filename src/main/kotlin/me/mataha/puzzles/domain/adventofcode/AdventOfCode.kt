package me.mataha.puzzles.domain.adventofcode

import me.mataha.puzzles.domain.Puzzle
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
@Puzzle<AdventOfCodeDay<*, *>, AdventOfCodeData<*, *>>(
    origin = AdventOfCode.NAME,
    factory = AdventOfCodeDataFactory::class
)
annotation class AdventOfCode(val task: String, val year: Int, val day: Int) {
    companion object {
        const val NAME = "Advent of Code"
    }
}
