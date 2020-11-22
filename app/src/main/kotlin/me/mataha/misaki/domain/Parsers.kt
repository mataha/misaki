package me.mataha.misaki.domain

fun interface Parser<in I : Any, out O : Any> {
    fun parse(input: I): O
}

typealias StringParser<O> = Parser<List<String>, O>

interface NoOpParser : StringParser<List<String>> {
    override fun parse(input: List<String>): List<String> = input
}

interface OneLineParser : StringParser<String> {
    override fun parse(input: List<String>): String = input.joinToString("")
}
