package me.mataha.misaki.domain

interface Solution<I : Any, out O : Any> : StringParser<I> {
    fun solve(input: I): O

    fun process(input: String): O = solve(parse(input))
}
