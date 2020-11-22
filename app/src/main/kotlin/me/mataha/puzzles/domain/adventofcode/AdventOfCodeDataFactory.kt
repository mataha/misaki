package me.mataha.puzzles.domain.adventofcode

import me.mataha.puzzles.domain.SolutionDataFactory
import me.mataha.puzzles.util.reflection.instance
import kotlin.reflect.KClass

object AdventOfCodeDataFactory : SolutionDataFactory<AdventOfCodeDay<*, *>, AdventOfCodeData<*, *>> {
    override fun create(solution: KClass<in AdventOfCodeDay<*, *>>): AdventOfCodeData<*, *> {
        val metadata = solution.annotations.first { annotation ->
            annotation.annotationClass == AdventOfCode::class
        } as AdventOfCode

        val instance = solution.instance() as AdventOfCodeDay<*, *>

        return AdventOfCodeData(
            metadata.task,
            metadata.year,
            metadata.day,
            instance
        )
    }
}
