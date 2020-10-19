package me.mataha.puzzles.solutions.adventofcode.aoc2015.d06

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import me.mataha.puzzles.solutions.adventofcode.aoc2015.d06.Instruction.Type

object InstructionGrammar: Grammar<Instruction>()
{
    @Suppress("unused")
    @JvmStatic private val whiteSpace by token("""\s+""", ignore = true)

    @JvmStatic private val COMMA by token(",")
    @JvmStatic private val TOGGLE by token("toggle")
    @JvmStatic private val TURN_ON by token("turn on")
    @JvmStatic private val TURN_OFF by token("turn off")
    @JvmStatic private val THROUGH by token("through")

    @JvmStatic private val numeral by token("""\d+""")

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

    override val rootParser by instruction
}

private data class Coordinates(val x: Int, val y: Int)
