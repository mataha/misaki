package me.mataha.misaki.domain

interface PuzzleData<I : Any, out O : Any> {
    val name: String
    val origin: String // Should return the same thing as [Puzzle.origin] does
    val solution: Solution<I, O>
}
