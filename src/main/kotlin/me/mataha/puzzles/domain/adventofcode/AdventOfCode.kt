package me.mataha.puzzles.domain.adventofcode

import me.mataha.puzzles.domain.Puzzle
import me.mataha.puzzles.domain.PuzzleOrigin

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Puzzle(PuzzleOrigin.ADVENT_OF_CODE)
annotation class AdventOfCode(val name: String, val year: Int, val day: Int)
