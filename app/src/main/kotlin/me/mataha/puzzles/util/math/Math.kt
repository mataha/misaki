@file:JvmName("Math")

package me.mataha.puzzles.util.math

import kotlin.math.max as mathMax
import kotlin.math.min as mathMin

/** Returns the smallest value among the supplied [Int] values. */
fun min(number: Int, vararg numbers: Int): Int = mathMin(numbers.minOrNull() ?: Int.MAX_VALUE, number)

/** Returns the largest value among the supplied [Int] values. */
fun max(number: Int, vararg numbers: Int): Int = mathMax(numbers.maxOrNull() ?: Int.MIN_VALUE, number)
