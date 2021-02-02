package me.mataha.misaki.solutions.adventofcode.aoc2015.d06

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.asJust
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.combinators.zeroOrMore
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import me.mataha.misaki.solutions.adventofcode.aoc2015.d06.Instruction.Type

internal object LightGridGrammar : Grammar<List<Instruction>>() {
    @Suppress("unused")
    private val whiteSpace by regexToken("""\s+""", ignore = true)

    private val COMMA by literalToken(",")
    private val THROUGH by literalToken("through")

    private val TOGGLE by literalToken("toggle")
    private val TURN_ON by literalToken("turn on")
    private val TURN_OFF by literalToken("turn off")

    private val numeral by regexToken("""\d+""")

    private val number by numeral use { text.toInt() }

    private val toggle by TOGGLE asJust Type.TOGGLE
    private val turnOn by TURN_ON asJust Type.TURN_ON
    private val turnOff by TURN_OFF asJust Type.TURN_OFF

    private val type
            by toggle or turnOn or turnOff

    private val coordinates
            by (number and -COMMA and number)
                .map { (x, y) -> Coordinates(x, y) }

    private val instruction
            by (type and coordinates and -THROUGH and coordinates)
                .map { (type, from, to) -> Instruction(type, IntRange(from.x, to.x), IntRange(from.y, to.y)) }

    private val instructions by zeroOrMore(instruction)

    override val rootParser by instructions
}

private data class Coordinates(val x: Int, val y: Int)
