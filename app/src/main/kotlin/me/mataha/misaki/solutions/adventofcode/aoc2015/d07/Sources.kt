package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

@ExperimentalUnsignedTypes
sealed class Source {
    internal abstract fun resolve(circuit: Circuit): UShort
}

@ExperimentalUnsignedTypes
internal data class Value(private val signal: UShort) : Source() {
    override fun resolve(circuit: Circuit): UShort = signal
}

@ExperimentalUnsignedTypes
internal data class Wire(private val identifier: String) : Source() {
    override fun resolve(circuit: Circuit): UShort = circuit[identifier]
}
