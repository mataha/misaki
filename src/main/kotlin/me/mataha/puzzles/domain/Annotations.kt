package me.mataha.puzzles.domain

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Puzzle(val origin: PuzzleOrigin)
