package me.mataha.misaki.util.extensions

/**
 * For each character in this char sequence other than the first and the last,
 * returns a list of pairs of two characters that are adjacent to it.
 *
 * The returned list is empty if this char sequence contains two or less characters.
 */
fun CharSequence.zipAdjacent(): List<Pair<Char, Char>> = zipAdjacent { a, b -> a to b }

/**
 * For each character in this char sequence other than the first and the last,
 * returns a list containing the results of applying the given [transform] function
 * to each pair of two characters that are adjacent to it.
 *
 * The returned list is empty if this char sequence contains two or less characters.
 */
inline fun <Result> CharSequence.zipAdjacent(transform: (a: Char, b: Char) -> Result): List<Result> {
    val size = length - 2
    if (size < 1) {
        return emptyList()
    }

    val result = ArrayList<Result>(size)
    for (index in 0 until size) {
        result.add(transform(this[index], this[index + 2]))
    }
    return result
}
