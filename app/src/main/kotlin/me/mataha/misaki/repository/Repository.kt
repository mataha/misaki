package me.mataha.misaki.repository

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import io.github.classgraph.ClassInfoList
import io.github.classgraph.ScanResult
import me.mataha.misaki.domain.Puzzle
import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.domain.PuzzleDataFactory
import me.mataha.misaki.domain.Solution
import kotlin.reflect.KClass

internal interface PuzzleRepository {
    fun get(origin: String, predicate: (task: String) -> Boolean): List<PuzzleData<*, *>>

    fun get(origin: String, task: String): PuzzleData<*, *>?

    fun getAll(): Map<String, List<PuzzleData<*, *>>>
}

internal class DefaultPuzzleRepository(vararg packages: String) : PuzzleRepository {
    override fun get(origin: String, predicate: (task: String) -> Boolean): List<PuzzleData<*, *>> =
        getAll()[origin]?.filter { task -> predicate(task.name) } ?: emptyList()

    override fun get(origin: String, task: String): PuzzleData<*, *>? =
        get(origin) { it == task }.firstOrNull()

    override fun getAll(): Map<String, List<PuzzleData<*, *>>> =
        puzzles

    private val puzzles: Map<String, List<PuzzleData<*, *>>> by lazy {
        ClassGraph()
            .acceptPackages(*packages)
            .enableAnnotationInfo()
            .scan()
            .use { result ->
                result
                    .getClassesWithAnnotation<Puzzle>()
                    .filter { info -> !info.isAnnotation } // discard meta-annotation uses
                    .groupBy({ info ->
                        val puzzle = info.loadAnnotation<Puzzle>()
                        puzzle.origin
                    }, { info ->
                        val puzzle = info.loadAnnotation<Puzzle>()
                        val factory = PuzzleDataFactory.get(puzzle.factory)
                        val solution = info.load<Solution<*, *>>()
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
