package me.mataha.misaki.solutions.adventofcode.aoc2015.d16

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar

internal object MemoirGrammar : Grammar<List<Memoir>>() {
    @Suppress("unused")
    private val whiteSpace by token("""\s+""", ignore = true)

    private val COLON by token(":")
    private val COMMA by token(",")

    private val SUE by token("Sue")

    private val string by token("""[a-z]+""")
    private val numeral by token("""\d+""")

    private val identifier by string use { text }
    private val number by numeral use { text.toInt() }

    private val sue
            by (-SUE and number)
                .map { number -> Sue(number) }

    private val compound
            by (identifier and -COLON and number)
                .map { (identifier, number) -> identifier to number }

    private val compounds
            by separatedTerms(compound, COMMA)
                .map { compounds -> compounds.associate { compound -> compound } }

    private val memoir
            by (sue and -COLON and compounds)
                .map { (aunt, compounds) -> Memoir(compounds, aunt) }

    private val memoirs by oneOrMore(memoir)

    override val rootParser by memoirs
}
