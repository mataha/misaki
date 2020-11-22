package me.mataha.puzzles.domain

import me.mataha.puzzles.util.reflection.instance
import kotlin.reflect.KClass

fun interface SolutionDataFactory<Solution : PuzzleSolution<*, *>, out Data : SolutionData<*, *>>{
    fun create(solution: KClass<in Solution>): Data

    companion object {
        @JvmStatic
        fun <Solution : PuzzleSolution<*, *>, Data : SolutionData<*, *>> get(
            factory: KClass<out SolutionDataFactory<out Solution, Data>>
        ): SolutionDataFactory<out Solution, Data> = factory.instance()
    }
}
