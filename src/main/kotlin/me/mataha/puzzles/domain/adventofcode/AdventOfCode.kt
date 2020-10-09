package me.mataha.puzzles.domain.adventofcode

import me.mataha.puzzles.domain.Puzzle
import me.mataha.puzzles.domain.PuzzleProvider

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Puzzle(PuzzleProvider.ADVENT_OF_CODE)
annotation class AdventOfCode(val name: String, val year: Int, val day: Int)
