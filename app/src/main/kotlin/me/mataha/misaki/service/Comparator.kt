package me.mataha.misaki.service

import me.mataha.misaki.util.text.levenshteinDistance as distance

internal interface EqualityComparator<in T> {
    fun compare(a: T, b: T): Boolean
}

internal class FuzzyEqualityComparator(private val threshold: Int) : EqualityComparator<String> {
    override fun compare(a: String, b: String): Boolean = distance(a.toLowerCase(), b.toLowerCase()) <= threshold
}
