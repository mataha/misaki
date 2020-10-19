package me.mataha.puzzles.solutions.adventofcode.aoc2015.d07

@ExperimentalUnsignedTypes
sealed class Source
{
    abstract fun resolve(circuit: Circuit): UShort
}

@ExperimentalUnsignedTypes
data class Value(val signal: UShort): Source()
{
    override fun resolve(circuit: Circuit): UShort = signal
}

@ExperimentalUnsignedTypes
data class Wire(val identifier: String): Source()
{
    override fun resolve(circuit: Circuit): UShort = circuit[identifier]
}
