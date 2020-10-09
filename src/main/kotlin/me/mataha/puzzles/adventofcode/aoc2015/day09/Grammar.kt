package me.mataha.puzzles.adventofcode.aoc2015.day09

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar

object LocationGrammar: Grammar<WeightedGraph<Location>>()
{
    @Suppress("unused")
    @JvmStatic private val whiteSpace by token("""\s+""", ignore = true)

    @JvmStatic private val TO by token("to")
    @JvmStatic private val EQUALS by token("=")

    @JvmStatic private val string by token("""[A-Za-z]+""")
    @JvmStatic private val numeral by token("""\d+""")

    private val identifier by string use { text }
    private val number by numeral use { text.toInt() }

    private val location by identifier map { name -> Location(name) }
    private val distance by number

    private val routes
            by oneOrMore(location and -TO and location and -EQUALS and distance)
                .map { routes ->
                    val locations = WeightedGraph<Location>()
                    routes.forEach { (a, b, distance) -> locations[a, b] = distance }
                    locations
                }

    override val rootParser by routes
}
