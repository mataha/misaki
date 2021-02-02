package me.mataha.misaki.solutions.adventofcode.aoc2015.d07

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

@ExperimentalUnsignedTypes
internal object CircuitGrammar : Grammar<List<Part>>() {
    @Suppress("unused")
    private val whiteSpace by regexToken("""\s+""", ignore = true)

    private val ARROW by literalToken("->")

    private val AND by literalToken("AND")
    private val OR by literalToken("OR")
    private val NOT by literalToken("NOT")
    private val LSHIFT by literalToken("LSHIFT")
    private val RSHIFT by literalToken("RSHIFT")

    private val string by regexToken("""[a-z]+""")
    private val numeral by regexToken("""-?\d+""")

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

    private val parts by oneOrMore(part)

    override val rootParser by parts
}
