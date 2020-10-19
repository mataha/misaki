package me.mataha.puzzles.domain

enum class PuzzleOrigin(val provider: String, val strings: List<String>)
{
    ADVENT_OF_CODE (
        "Advent of Code",
        listOf("Advent of Code", "aoc", "adventofcode", "AoC")
    )
}
