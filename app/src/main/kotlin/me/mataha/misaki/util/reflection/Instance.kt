package me.mataha.misaki.util.reflection

import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.valueParameters

/**
 * Returns an instance of this class if it either:
 *  - is an object;
 *  - has a public, parameterless constructor.
 */
fun <R : Any> KClass<out R>.instance(): R {
    val instance = constructors.firstOrNull { constructor ->
        constructor.isPublic() && constructor.isParameterless()
    }?.call() ?: objectInstance

    return requireNotNull(instance) {
        "$qualifiedName is neither an object nor a class with a public, parameterless constructor."
    }
}

private fun <R> KCallable<R>.isParameterless(): Boolean = valueParameters.isEmpty()

private fun <R> KCallable<R>.isPublic(): Boolean = visibility == KVisibility.PUBLIC
