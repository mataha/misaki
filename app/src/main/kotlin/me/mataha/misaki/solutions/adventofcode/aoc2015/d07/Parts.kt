package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import me.mataha.misaki.util.extensions.shl
import me.mataha.misaki.util.extensions.shr

sealed class Part(internal open val identifier: String) {
    internal abstract fun getSignal(circuit: Circuit): UShort
}

internal data class NoOpGate(
    override val identifier: String,
    private val source: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = source.resolve(circuit)
}

internal data class AndGate(
    override val identifier: String,
    private val a: Source,
    private val b: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) and b.resolve(circuit)
}

internal data class OrGate(
    override val identifier: String,
    private val a: Source,
    private val b: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) or b.resolve(circuit)
}

internal data class NotGate(
    override val identifier: String,
    private val source: Source
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = source.resolve(circuit).inv()
}

internal data class LeftShiftGate(
    override val identifier: String,
    private val a: Source,
    private val bitCount: Int
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) shl bitCount
}

internal data class RightShiftGate(
    override val identifier: String,
    private val a: Source,
    private val bitCount: Int
) : Part(identifier) {
    override fun getSignal(circuit: Circuit): UShort = a.resolve(circuit) shr bitCount
}
