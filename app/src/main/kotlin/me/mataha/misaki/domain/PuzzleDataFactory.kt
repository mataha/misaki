package me.mataha.misaki.domain

import me.mataha.misaki.util.reflection.instance
import kotlin.reflect.KClass

fun interface PuzzleDataFactory {
    fun create(solution: KClass<out Solution<*, *>>): PuzzleData<*, *>

    companion object {
        @JvmStatic fun get(factory: KClass<out PuzzleDataFactory>): PuzzleDataFactory = factory.instance()
    }
}
