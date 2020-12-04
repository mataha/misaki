package me.mataha.misaki.domain

interface SolutionData<I : Any, out O : Any> {
    val name: String
    val solution: Solution<I, O>
}
