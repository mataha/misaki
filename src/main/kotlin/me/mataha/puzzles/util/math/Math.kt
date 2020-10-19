@file:JvmName("Math")

package me.mataha.puzzles.util.math

fun min(number: Int, vararg numbers: Int): Int
        = kotlin.math.min(numbers.minOrNull() ?: Int.MAX_VALUE, number)

fun max(number: Int, vararg numbers: Int): Int
        = kotlin.math.max(numbers.maxOrNull() ?: Int.MIN_VALUE, number)