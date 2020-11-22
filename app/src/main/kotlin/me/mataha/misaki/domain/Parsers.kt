package me.mataha.misaki.domain

fun interface Parser<in Input : Any, out Output : Any> {
    fun parse(input: Input): Output
}

typealias StringParser<Output> = Parser<List<String>, Output>

interface NoOpParser : StringParser<List<String>> {
    override fun parse(input: List<String>): List<String> = input
}

interface OneLineParser : StringParser<String> {
    override fun parse(input: List<String>): String = input.joinToString("")
}
