package me.mataha.misaki.domain

fun interface Parser<in I : Any, out O : Any> {
    fun parse(input: I): O
}

typealias StringParser<O> = Parser<String, O>

interface NoOpParser : StringParser<String> {
    override fun parse(input: String): String = input
}

interface LineParser : StringParser<List<String>> {
    override fun parse(input: String): List<String> = input.lines()
}
