package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.Solution
import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.domain.SolutionDataFactory
import me.mataha.misaki.util.reflection.instance
import kotlin.reflect.KClass

object AdventOfCodeDataFactory : SolutionDataFactory {
    override fun create(solution: KClass<out Solution<*, *>>): SolutionData<*, *> {
        val metadata = solution.annotations.first { annotation ->
            annotation.annotationClass == AdventOfCode::class
        } as AdventOfCode

        val instance = solution.instance()

        return AdventOfCodeData(
            name = metadata.task,
            year = metadata.year,
            day = metadata.day,
            solution = instance
        )
    }
}
