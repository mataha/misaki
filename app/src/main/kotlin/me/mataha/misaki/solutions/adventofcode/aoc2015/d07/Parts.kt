package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import me.mataha.misaki.util.extensions.shl
import me.mataha.misaki.util.extensions.shr

@ExperimentalUnsignedTypes
sealed class Part(internal open val identifier: String) {
    internal abstract fun getSignal(circuit: Circuit): UShort
}

@ExperimentalUnsignedTypes
internal data class NoOpGate(
    override val identifier: String,
    private val source: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = source.resolve(circuit)
}

@ExperimentalUnsignedTypes
internal data class AndGate(
    override val identifier: String,
    private val a: Source,
    private val b: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) and b.resolve(circuit)
}

@ExperimentalUnsignedTypes
internal data class OrGate(
    override val identifier: String,
    private val a: Source,
    private val b: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) or b.resolve(circuit)
}

@ExperimentalUnsignedTypes
internal data class NotGate(
    override val identifier: String,
    private val source: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = source.resolve(circuit).inv()
}

@ExperimentalUnsignedTypes
internal data class LeftShiftGate(
    override val identifier: String,
    private val a: Source,
    private val bitCount: Int
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) shl bitCount
}

@ExperimentalUnsignedTypes
internal data class RightShiftGate(
    override val identifier: String,
    private val a: Source,
    private val bitCount: Int
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) shr bitCount
}
