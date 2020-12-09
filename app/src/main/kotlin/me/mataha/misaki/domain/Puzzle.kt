package me.mataha.misaki.domain

import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.reflect.KClass

@Target(ANNOTATION_CLASS)
@Repeatable
annotation class Puzzle(
    val origin: String,
    val handles: Array<String> = [],
    val factory: KClass<out PuzzleDataFactory>,
)
