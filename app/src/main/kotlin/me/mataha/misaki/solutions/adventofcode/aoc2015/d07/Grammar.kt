package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar

@ExperimentalUnsignedTypes
internal object CircuitGrammar : Grammar<Part>() {
    @Suppress("unused")
    private val whiteSpace by token("""\s+""", ignore = true)

    private val ARROW by token("->")
    private val AND by token("AND")
    private val OR by token("OR")
    private val NOT by token("NOT")
    private val LSHIFT by token("LSHIFT")
    private val RSHIFT by token("RSHIFT")

    private val string by token("""[a-z]+""")
    private val numeral by token("""-?\d+""")

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
