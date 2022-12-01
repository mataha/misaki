package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

sealed class Source {
    internal abstract fun resolve(circuit: Circuit): UShort
}

internal data class Value(private val signal: UShort) : Source() {
    override fun resolve(circuit: Circuit): UShort = signal
}

internal data class Wire(private val identifier: String) : Source() {
    override fun resolve(circuit: Circuit): UShort = circuit[identifier]
}
