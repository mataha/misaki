package me.mataha.puzzles.service

import me.mataha.puzzles.util.text.levenshteinDistance

interface IEqualityComparator<in Type> {
    fun compare(a: Type, b: Type): Boolean
}

class FuzzyEqualityComparator : IEqualityComparator<String> {
    override fun compare(a: String, b: String): Boolean = levenshteinDistance(a.toLowerCase(), b.toLowerCase()) <= 2
}
