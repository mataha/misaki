package me.mataha.misaki.solutions.adventofcode.aoc2015.d09

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

internal object LocationGrammar : Grammar<WeightedGraph<Location>>() {
    @Suppress("unused")
    private val whiteSpace by regexToken("""\s+""", ignore = true)

    private val TO by literalToken("to")
    private val EQUALS by literalToken("=")

    private val string by regexToken("""[A-Za-z]+""")
    private val numeral by regexToken("""\d+""")

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
