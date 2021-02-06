package me.mataha.misaki.domain.adventofcode

import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.domain.PuzzleDataFactory
import me.mataha.misaki.domain.Solution
import me.mataha.misaki.util.reflection.findAnnotation
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object AdventOfCodeDataFactory : PuzzleDataFactory {
    override fun create(solution: KClass<out Solution<*, *>>): PuzzleData<*, *> {
        val metadata = solution.findAnnotation<AdventOfCode>()
        val instance = solution.createInstance()

        return AdventOfCodeData(
            name = metadata.task,
            year = metadata.year,
            day = metadata.day,
            solution = instance
        )
    }
}
