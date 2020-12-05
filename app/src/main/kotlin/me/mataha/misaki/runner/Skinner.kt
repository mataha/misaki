package me.mataha.misaki.runner

internal fun interface Skinner<T> {
    fun skin(value: T): T
}
