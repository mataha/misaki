package me.mataha.misaki.service

import me.mataha.misaki.util.text.levenshteinDistance

interface IEqualityComparator<in T> {
    fun compare(a: T, b: T): Boolean
}

class FuzzyEqualityComparator : IEqualityComparator<String> {
    override fun compare(a: String, b: String): Boolean = levenshteinDistance(a.toLowerCase(), b.toLowerCase()) <= 2
}
