package me.mataha.misaki.domain

interface PuzzleData<I : Any, out O : Any> {
    val name: String
    val solution: Solution<I, O>
}
