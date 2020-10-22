package me.mataha.puzzles.util.annotations

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.*

@Target(FUNCTION, PROPERTY, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention(SOURCE)
internal annotation class InlineCall
