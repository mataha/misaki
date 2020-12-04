package me.mataha.misaki.domain

import me.mataha.misaki.util.reflection.instance
import kotlin.reflect.KClass

fun interface SolutionDataFactory {
    fun create(solution: KClass<out Solution<*, *>>): SolutionData<*, *>

    companion object {
        @JvmStatic fun get(factory: KClass<out SolutionDataFactory>): SolutionDataFactory = factory.instance()
    }
}
