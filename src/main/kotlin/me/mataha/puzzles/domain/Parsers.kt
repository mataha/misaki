package me.mataha.puzzles.domain

interface Parser<in Input, out Output> {
    fun parse(input: Input): Output
}

typealias StringParser<Output> = Parser<List<String>, Output>

interface NoOpParser : StringParser<List<String>> {
    override fun parse(input: List<String>): List<String> = input
}

interface OneLineParser : StringParser<String> {
    override fun parse(input: List<String>): String = input.joinToString("")
}
