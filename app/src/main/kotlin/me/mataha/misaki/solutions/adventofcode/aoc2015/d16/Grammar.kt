package me.mataha.misaki.solutions.adventofcode.aoc2015.d16

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

internal object MemoirGrammar : Grammar<List<Memoir>>() {
    @Suppress("unused")
    private val whiteSpace by regexToken("""\s+""", ignore = true)

    private val COLON by literalToken(":")
    private val COMMA by literalToken(",")

    private val SUE by literalToken("Sue")

    private val string by regexToken("""[a-z]+""")
    private val numeral by regexToken("""\d+""")

    private val identifier by string use { text }
    private val number by numeral use { text.toInt() }

    private val aunt
            by (-SUE and number)
                .map { number -> Sue(number) }

    private val compound
            by (identifier and -COLON and number)
                .map { (identifier, number) -> identifier to number }

    private val compounds
            by separatedTerms(compound, COMMA)
                .map { compounds -> compounds.associate { compound -> compound } }

    private val memoir
            by (aunt and -COLON and compounds)
                .map { (aunt, compounds) -> Memoir(compounds, aunt) }

    private val memoirs by oneOrMore(memoir)

    override val rootParser by memoirs
}
