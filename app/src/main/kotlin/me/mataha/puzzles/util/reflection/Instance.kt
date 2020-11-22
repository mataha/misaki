@file:JvmName("Instance")

package me.mataha.puzzles.util.reflection

import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.valueParameters

/**
 * Returns an instance of this class if it either:
 *  - is an object;
 *  - has a public, parameterless constructor.
 */
fun <Result : Any> KClass<out Result>.instance(): Result {
    val instance = constructors.firstOrNull { constructor ->
        constructor.isPublic() && constructor.isParameterless()
    }?.call() ?: objectInstance

    return requireNotNull(instance) {
        "$qualifiedName is neither an object nor a class with a public, parameterless constructor."
    }
}

private fun <Result> KCallable<Result>.isParameterless(): Boolean = valueParameters.isEmpty()

private fun <Result> KCallable<Result>.isPublic(): Boolean = visibility == KVisibility.PUBLIC
