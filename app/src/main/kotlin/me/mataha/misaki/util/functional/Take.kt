@file:Suppress("NOTHING_TO_INLINE")

package me.mataha.misaki.util.functional

import me.mataha.misaki.util.internal.annotations.InlineCall

/**
 * Given `this` receiver object, just returns the [value] instead.
 */
@InlineCall
inline infix fun <T> T.take(value: T) : T = value
