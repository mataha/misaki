package me.mataha.misaki.domain

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Target(CLASS, ANNOTATION_CLASS)
@Retention(RUNTIME)
@Repeatable
annotation class Puzzle<S : PuzzleSolution<*, *>, out D : SolutionData<*, *>>(
    val origin: String,
    val factory: KClass<out SolutionDataFactory<S, D>>,
    val handles: Array<String> = [], // TODO: unused for now
)
