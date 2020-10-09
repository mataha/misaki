package me.mataha.puzzles.util.extensions

@Suppress("NOTHING_TO_INLINE")
inline infix fun UShort.shl(bitCount: Int): UShort = (this.toUInt() shl bitCount).toUShort()

@Suppress("NOTHING_TO_INLINE")
inline infix fun UShort.shr(bitCount: Int): UShort = (this.toUInt() shr bitCount).toUShort()
