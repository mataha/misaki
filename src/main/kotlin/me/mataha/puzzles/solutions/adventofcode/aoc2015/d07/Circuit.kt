package me.mataha.puzzles.solutions.adventofcode.aoc2015.d07

@ExperimentalUnsignedTypes
class Circuit private constructor()
{
    private lateinit var parts: Map<String, Part>

    private val signals: MutableMap<String, UShort> by lazy { mutableMapOf() }

    operator fun get(wireIdentifier: String): UShort
            = signals[wireIdentifier] ?: computeSignal(wireIdentifier)

    operator fun set(wireIdentifier: String, signal: UShort): Circuit
            = apply { signals[wireIdentifier] = signal }

    fun reset(): Circuit
            = apply { signals.clear() }

    private fun computeSignal(wireIdentifier: String): UShort
    {
        val part = requireNotNull(parts[wireIdentifier]) {
            "Part with identifier '$wireIdentifier' does not exist."
        }

        val signal = part.getSignal(this)
        signals[wireIdentifier] = signal

        return signal
    }

    companion object
    {
        fun of(parts: List<Part> = listOf()): Circuit
        {
            val circuit = Circuit()

            circuit.parts = parts.associateBy { part -> part.identifier }

            return circuit
        }
    }
}
