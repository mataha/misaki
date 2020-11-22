package me.mataha.puzzles.service

interface IStringService {
    fun prepare(string: String): String
}

class StringService : IStringService {
    override fun prepare(string: String): String = string.trim()
}
