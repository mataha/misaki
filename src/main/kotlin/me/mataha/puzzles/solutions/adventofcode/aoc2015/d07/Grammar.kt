package me.mataha.puzzles.solutions.adventofcode.aoc2015.d07

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar

@ExperimentalUnsignedTypes
internal object CircuitGrammar: Grammar<Part>()
{
    @Suppress("unused")
    @JvmStatic private val whiteSpace by token("""\s+""", ignore = true)

    @JvmStatic private val ARROW by token("->")
    @JvmStatic private val AND by token("AND")
    @JvmStatic private val OR by token("OR")
    @JvmStatic private val NOT by token("NOT")
    @JvmStatic private val LSHIFT by token("LSHIFT")
    @JvmStatic private val RSHIFT by token("RSHIFT")

    @JvmStatic private val string by token("""[a-z]+""")
    @JvmStatic private val numeral by token("""-?\d+""")

    private val identifier by string use { text }
    private val number by numeral use { text.toInt() }
    private val signal by numeral use { text.toUShort() }

    private val bitShift by number
    private val value by signal map { signal -> Value(signal) }
    private val wire by identifier map { identifier -> Wire(identifier) }
    private val source by value or wire

    private val noOpGate
            by (source and -ARROW and identifier)
                .map { (source, identifier) -> NoOpGate(identifier, source) }

    private val andGate
            by (source and -AND and source and -ARROW and identifier)
                .map { (a, b, identifier) -> AndGate(identifier, a, b) }

    private val orGate
            by (source and -OR and source and -ARROW and identifier)
                .map { (a, b, identifier) -> OrGate(identifier, a, b) }

    private val notGate
            by (-NOT and source and -ARROW and identifier)
                .map { (source, identifier) -> NotGate(identifier, source) }

    private val leftShiftGate
            by (source and -LSHIFT and bitShift and -ARROW and identifier)
                .map { (source, bitShift, identifier) -> LeftShiftGate(identifier, source, bitShift) }

    private val rightShiftGate
            by (source and -RSHIFT and bitShift and -ARROW and identifier)
                .map { (source, bitShift, identifier) -> RightShiftGate(identifier, source, bitShift) }

    private val part
            by noOpGate or andGate or orGate or notGate or leftShiftGate or rightShiftGate

    override val rootParser by part
}
