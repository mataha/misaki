package me.mataha.misaki.solutions.adventofcode

import me.mataha.misaki.solutions.PuzzleNameGeneration
import org.junit.jupiter.api.Tag
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FILE

@PuzzleNameGeneration
@Tag(AdventOfCodeTest.TAG)
@Target(CLASS, FILE)
annotation class AdventOfCodeTest {
    companion object {
        const val TAG = "advent-of-code"
    }
}
