package me.mataha.misaki.util.functional

import me.mataha.misaki.util.annotations.InlineCall
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

/**
 * Do [something], then return the original [value].
 *
 * While this function is not far from representing the
 * [K-combinator](https://www.wolframscience.com/nks/notes-11-12--combinators/)
 * (*constancy*), its intended use is to allow 'assignment as expression'
 * syntax. Consider inline swaps, e.g.:
 *
 * ```kt
 * var a = 2
 * var b = 3
 * a = assign(b) { b = a }
 * assert(a == 3 && b == 2)
 * ```
 */
@ExperimentalContracts
@InlineCall
inline fun <T> assign(value: T, crossinline something: (T) -> Unit): T {
    contract {
        callsInPlace(something, EXACTLY_ONCE)
    }
    return value.also(something) // `also` has an identical contract
}
