@file:Suppress("NOTHING_TO_INLINE")

package me.mataha.misaki.util.extensions

import me.mataha.misaki.util.internal.annotations.InlineCall

/**
 * Shifts this value left by the [bitCount] number of bits.
 *
 * Converts to [UInt], then to [UShort] under the hood.
 */
@InlineCall
inline infix fun UShort.shl(bitCount: Int): UShort = (this.toUInt() shl bitCount).toUShort()

/**
 * Shifts this value right by the [bitCount] number of bits,
 * filling the leftmost bits with zeroes.
 *
 * Converts to [UInt], then to [UShort] under the hood.
 */
@InlineCall
inline infix fun UShort.shr(bitCount: Int): UShort = (this.toUInt() shr bitCount).toUShort()
