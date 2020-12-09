package me.mataha.misaki.util.internal.annotations

import kotlin.annotation.AnnotationTarget.*

@Target(FUNCTION, PROPERTY, PROPERTY_GETTER, PROPERTY_SETTER)
internal annotation class InlineCall
