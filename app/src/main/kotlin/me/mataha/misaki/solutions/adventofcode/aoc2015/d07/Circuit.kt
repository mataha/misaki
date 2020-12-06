package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

@ExperimentalUnsignedTypes
class Circuit private constructor() {
    private lateinit var parts: Map<String, Part>

    private val signals: MutableMap<String, UShort> by lazy { mutableMapOf() }

    internal operator fun get(wireIdentifier: String): UShort =
        signals[wireIdentifier] ?: computeSignal(wireIdentifier)

    internal operator fun set(wireIdentifier: String, signal: UShort): Circuit =
        apply { signals[wireIdentifier] = signal }

    internal fun reset(): Circuit = apply { signals.clear() }

    private fun computeSignal(wireIdentifier: String): UShort {
        val part = parts.getValue(wireIdentifier)

        val signal = part.getSignal(this)
        signals[wireIdentifier] = signal

        return signal
    }

    internal companion object {
        internal fun of(parts: List<Part> = listOf()): Circuit {
            val circuit = Circuit()
            circuit.parts = parts.associateBy { part -> part.identifier }
            return circuit
        }
    }
}
