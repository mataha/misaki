package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Puzzle
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

// (15..20).forEach { i -> (1..25).forEach { j -> println("/** See [https://adventofcode.com/20$i/day/$j]. */") } }
@Target(CLASS)
@Retention(RUNTIME)
@Puzzle(origin = AdventOfCode.NAME, factory = AdventOfCodeDataFactory::class)
annotation class AdventOfCode(val task: String, val year: Int, val day: Int) {
    companion object {
        const val NAME = "Advent of Code"
    }
}
