@file:Suppress("NOTHING_TO_INLINE")

package me.mataha.puzzles.util.extensions

inline infix fun UShort.shl(bitCount: Int): UShort = (this.toUInt() shl bitCount).toUShort()

inline infix fun UShort.shr(bitCount: Int): UShort = (this.toUInt() shr bitCount).toUShort()
