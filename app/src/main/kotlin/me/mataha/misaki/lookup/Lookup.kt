package me.mataha.misaki.lookup

import io.github.classgraph.ClassGraph
import me.mataha.misaki.ROOT
import me.mataha.misaki.domain.Puzzle
import me.mataha.misaki.domain.PuzzleSolution
import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.domain.SolutionDataFactory

interface ISolutionLookup {
    fun get(origin: String, predicate: (task: String) -> Boolean): List<SolutionData<*, *>>

    fun get(origin: String, task: String): SolutionData<*, *>?
}

class SolutionLookup : ISolutionLookup {
    override fun get(origin: String, predicate: (task: String) -> Boolean): List<SolutionData<*, *>> =
        solutions[origin]?.filter { solution -> predicate(solution.name) } ?: emptyList()

    override fun get(origin: String, task: String): SolutionData<*, *>? =
        get(origin) { name -> name == task }.firstOrNull()

    companion object {
        @JvmStatic
        private val solutions: SolutionMap by lazy(LazyThreadSafetyMode.NONE) { discover(ROOT) }

        @JvmStatic
        private fun discover(vararg packages: String): SolutionMap {
            val map: SolutionMap = ClassGraph()
                .acceptPackages(*packages)
                .enableAnnotationInfo()
                .scan()
                .use { result ->
                    result
                        .getClassesWithAnnotation(Puzzle::class.java.name)
                        .filter { info -> !info.isAnnotation }
                        .groupBy ({ info ->
                            val puzzle = info.annotationInfo[Puzzle::class.java.name].loadClassAndInstantiate() as Puzzle<*, *>

                            puzzle.origin
                        }, { info ->
                            val puzzle = info.annotationInfo[Puzzle::class.java.name].loadClassAndInstantiate() as Puzzle<*, *>
                            val factory = puzzle.factory

                            val solution = info.loadClass(PuzzleSolution::class.java).kotlin
                            val x = SolutionDataFactory.get(factory)
                            x.create(solution)
                        })
                }
            println(map)
            return map
        }
    }
}

private typealias SolutionMap = Map<String, List<SolutionData<*, *>>>
