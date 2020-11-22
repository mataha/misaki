package me.mataha.misaki.domain

import me.mataha.misaki.util.reflection.instance
import kotlin.reflect.KClass

fun interface SolutionDataFactory<S : PuzzleSolution<*, *>, out D : SolutionData<*, *>> {
    fun create(solution: KClass<in S>): D

    companion object {
        @JvmStatic
        fun <S : PuzzleSolution<*, *>, D : SolutionData<*, *>> get(
            factory: KClass<out SolutionDataFactory<out S, D>>
        ): SolutionDataFactory<out S, D> = factory.instance()
    }
}
