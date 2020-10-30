package me.mataha.puzzles.util.extensions

/**
 * Returns the next element of an enum; that is - the next element as
 * defined by the natural order of the elements (the order in which the enum
 * constants are declared).
 *
 * **Circular** (this function will return the first element of an enum
 * after the last).
 */
inline fun <reified E : Enum<E>> E.nextCircular(): E {
    val values = enumValues<E>()
    val ordinal = (this.ordinal + 1) % values.size
    return values[ordinal]
}
