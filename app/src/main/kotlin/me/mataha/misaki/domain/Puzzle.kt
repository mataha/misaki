package me.mataha.misaki.domain

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Target(CLASS, ANNOTATION_CLASS)
@Retention(RUNTIME)
@Repeatable
annotation class Puzzle<Solution : PuzzleSolution<*, *>, out Data : SolutionData<*, *>>(
    val origin: String,
    val factory: KClass<out SolutionDataFactory<Solution, Data>>,
    val handles: Array<String> = [], // TODO: unused for now
)

/* What would happen if [factory] didn't have the `out` variance annotation? Well... the following
 * throws two warnings (useless cast + unchecked cast), but does what it's supposed to do:
 *
 * ```kt
 * object TestDataFactory : SolutionDataFactory<PuzzleSolution<*, *>, SolutionData<*, *>> {
 *     override fun create(solution: KClass<out PuzzleSolution<*, *>>): SolutionData<*, *> = TO DO()
 * }
 *
 * @Puzzle(
 *     name = "Test",
 *     factory = (TestDataFactory as SolutionDataFactory<*, *>)::class as KClass<SolutionDataFactory<*, *>>,
 * )
 * annotation class TestPuzzle
 * ```
 */
