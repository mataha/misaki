@file:JvmName("Math")

package me.mataha.puzzles.util.math

/** Returns the smallest value among the supplied [Int] values. */
fun min(number: Int, vararg numbers: Int): Int
        = kotlin.math.min(numbers.minOrNull() ?: Int.MAX_VALUE, number)

/** Returns the largest value among the supplied [Int] values. */
fun max(number: Int, vararg numbers: Int): Int
        = kotlin.math.max(numbers.maxOrNull() ?: Int.MIN_VALUE, number)
