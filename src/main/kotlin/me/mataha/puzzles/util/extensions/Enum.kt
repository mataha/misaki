package me.mataha.puzzles.util.extensions

inline fun <reified E: Enum<E>> E.previous(): E
{
    val values = enumValues<E>()
    val ordinal = (this.ordinal + values.size - 1) % values.size
    return values[ordinal]
}

inline fun <reified E: Enum<E>> E.next(): E
{
    val values = enumValues<E>()
    val ordinal = (this.ordinal + 1) % values.size
    return values[ordinal]
}
