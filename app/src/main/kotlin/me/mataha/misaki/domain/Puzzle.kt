package me.mataha.misaki.domain

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Target(CLASS, ANNOTATION_CLASS)
@Retention(RUNTIME)
@Repeatable
annotation class Puzzle(
    val origin: String,
    val handles: Array<String> = [],
    val factory: KClass<out SolutionDataFactory>,
)
