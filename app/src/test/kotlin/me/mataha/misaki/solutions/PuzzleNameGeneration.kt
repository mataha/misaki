package me.mataha.misaki.solutions

import org.junit.jupiter.api.DisplayNameGeneration
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FILE

@DisplayNameGeneration(PuzzleNameGenerator::class)
@Target(CLASS, FILE)
annotation class PuzzleNameGeneration
