package me.mataha.misaki.service

internal fun interface EqualityComparator<in T> {
    fun compare(a: T, b: T): Boolean
}
