package me.mataha.misaki.solutions.adventofcode.aoc2015.d09

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar

internal object LocationGrammar : Grammar<WeightedGraph<Location>>() {
    @Suppress("unused")
    private val whiteSpace by token("""\s+""", ignore = true)

    private val TO by token("to")
    private val EQUALS by token("=")

    private val string by token("""[A-Za-z]+""")
    private val numeral by token("""\d+""")

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
