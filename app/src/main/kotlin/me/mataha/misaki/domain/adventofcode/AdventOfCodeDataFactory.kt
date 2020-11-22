package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.SolutionDataFactory
import me.mataha.misaki.util.reflection.instance
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
