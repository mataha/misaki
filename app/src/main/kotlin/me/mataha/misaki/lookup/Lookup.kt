package me.mataha.misaki.lookup

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import io.github.classgraph.ClassInfoList
import io.github.classgraph.ScanResult
import me.mataha.misaki.domain.Puzzle
import me.mataha.misaki.domain.PuzzleSolution
import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.domain.SolutionDataFactory
import kotlin.reflect.KClass

internal interface SolutionLookup {
    fun get(origin: String, predicate: (task: String) -> Boolean): List<SolutionData<*, *>>

    fun get(origin: String, task: String): SolutionData<*, *>?

    fun getAll(): Map<String, List<SolutionData<*, *>>>
}

internal class DefaultSolutionLookup(vararg packages: String) : SolutionLookup {
    override fun get(origin: String, predicate: (task: String) -> Boolean): List<SolutionData<*, *>> =
        getAll()[origin]?.filter { solution -> predicate(solution.name) } ?: emptyList()

    override fun get(origin: String, task: String): SolutionData<*, *>? =
        get(origin) { it == task }.firstOrNull()

    override fun getAll(): Map<String, List<SolutionData<*, *>>> =
        solutions

    private val solutions: Map<String, List<SolutionData<*, *>>> by lazy {
        ClassGraph()
            .acceptPackages(*packages)
            .enableAnnotationInfo()
            .scan()
            .use { result ->
                result
                    .getClassesWithAnnotation<Puzzle<*, *>>()
                    .filter { info -> !info.isAnnotation } // discard meta-annotations
                    .groupBy({ info ->
                        val puzzle = info.loadAnnotation<Puzzle<*, *>>()
                        puzzle.origin
                    }, { info ->
                        val puzzle = info.loadAnnotation<Puzzle<*, *>>()
                        val factory = SolutionDataFactory.get(puzzle.factory)
                        val solution = info.load<PuzzleSolution<*, *>>()
                        factory.create(solution)
                    })
            }
    }
}

/** Loads the class named by this object as a KClass. */
private inline fun <reified T : Any> ClassInfo.load(): KClass<T> =
    this.loadClass(T::class.java).kotlin

/** Returns a list of classes with the named class annotation or meta-annotation. */
private inline fun <reified A : Annotation> ScanResult.getClassesWithAnnotation(): ClassInfoList =
    this.getClassesWithAnnotation(A::class.java.name)

/** Loads and instantiates an annotation if it's present on this class. */
private inline fun <reified A : Annotation> ClassInfo.loadAnnotation(): A =
    this.annotationInfo[A::class.java.name]?.loadClassAndInstantiate() as? A
        ?: throw AnnotationNotPresentException("Annotation '${A::class.qualifiedName}' was not present on this class")

private class AnnotationNotPresentException(message: String?) : NoSuchElementException(message)
