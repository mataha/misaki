@file:Suppress("NOTHING_TO_INLINE")

package me.mataha.misaki.util.reflection

import me.mataha.misaki.util.internal.annotations.InlineCall
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.valueParameters

/**
 * Returns an instance of this class if it either:
 *  - is an object;
 *  - has a single public constructor with no parameters.
 *
 * Throws an [IllegalArgumentException] otherwise.
 */
fun <R : Any> KClass<R>.instance(): R {
    val instance = constructors.singleOrNull { constructor ->
        constructor.isPublic() && constructor.isNoArg()
    }?.call() ?: objectInstance

    return requireNotNull(instance) {
        "$this is neither an object nor a class with a single public, no-arg constructor"
    }
}

@InlineCall
private inline fun <R> KCallable<R>.isNoArg(): Boolean = valueParameters.isEmpty()

@InlineCall
private inline fun <R> KCallable<R>.isPublic(): Boolean = visibility == KVisibility.PUBLIC
