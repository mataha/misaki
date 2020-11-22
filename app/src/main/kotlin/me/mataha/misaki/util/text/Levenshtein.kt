@file:JvmName("Levenshtein")

package me.mataha.misaki.util.text

import me.mataha.misaki.util.functional.assign
import me.mataha.misaki.util.math.min

/**
 * Calculates [Levenshtein distance](https://en.wikipedia.org/wiki/Levenshtein_distance)
 * between two character sequences. A higher score indicates a greater distance.
 *
 * Only deletions, insertions and substitutions are included among its allowable
 * operations.
 *
 * Implementation based on [`unlimitedCompare`](https://github.com/apache/commons-text/blob/master/src/main/java/org/apache/commons/text/similarity/LevenshteinDistance.java#L341-L399)
 * from Apache Commons Text (licensed under ASL 2.0).
 */
fun levenshteinDistance(a: CharSequence, b: CharSequence): Int {
    var first = a
    var second = b

    if (first == second) return 0
    if (first.isEmpty()) return second.length
    if (second.isEmpty()) return first.length

    if (first.length > second.length) {
        first = assign(second) { second = first }
    }

    val distances = IntArray(first.length + 1) { it }

    for (j in 1..second.length) {
        var corner = distances[0]
        distances[0] = j

        for (i in 1..first.length) {
            val upper = distances[i]

            val deletion = distances[i - 1] + 1
            val insertion = distances[i] + 1
            val substitution = corner + if (first[i - 1] == second[j - 1]) 0 else 1
            distances[i] = min(deletion, insertion, substitution)

            corner = upper
        }
    }

    return distances[first.length]
}
