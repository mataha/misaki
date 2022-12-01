package me.mataha.misaki.util.reflection

import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.full.findAnnotation

/**
 * Returns an annotation of the given type on this element if it's present.
 *
 * Throws an [AnnotationNotPresentException] otherwise.
 */
inline fun <reified A : Annotation> KAnnotatedElement.findAnnotation(): A =
    findAnnotationOrElse {
        throw AnnotationNotPresentException("Annotation '${A::class.qualifiedName}' was not present on this class")
    }

/**
 * Returns an annotation of the given type on this element if it's present,
 * or the result of the [default] function otherwise.
 */
inline fun <reified A : Annotation> KAnnotatedElement.findAnnotationOrElse(default: () -> A): A =
    findAnnotation() ?: default()

/**
 * Thrown when the code tries to return an annotation that's not present
 * on an alement.
 */
class AnnotationNotPresentException(message: String?) : NoSuchElementException(message)
