package me.mataha.misaki.runner

internal fun interface Trimmer<T> {
    fun trim(value: T): T
}
