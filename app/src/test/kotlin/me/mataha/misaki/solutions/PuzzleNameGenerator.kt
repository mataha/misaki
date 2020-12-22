package me.mataha.misaki.solutions

import me.mataha.misaki.domain.Puzzle
import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.domain.PuzzleDataFactory
import me.mataha.misaki.domain.Solution
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.junit.platform.commons.support.AnnotationSupport.findAnnotation
import org.junit.platform.commons.support.AnnotationSupport.isAnnotated
import org.junit.platform.commons.support.ReflectionSupport
import java.lang.reflect.Method
import kotlin.reflect.KClass

open class PuzzleNameGenerator : DisplayNameGenerator.Simple() {
    override fun generateDisplayNameForClass(testClass: Class<*>): String {
        val name = stripTestSuffix(super.generateDisplayNameForClass(testClass))
        val puzzle = PuzzleLookup[name]

        return puzzle?.let { data ->
            generateDisplayNameForPuzzle(data)
        } ?: splitPascamelCasedWords(name)
    }

    override fun generateDisplayNameForNestedClass(nestedClass: Class<*>): String =
        splitPascamelCasedWords(super.generateDisplayNameForNestedClass(nestedClass))

    override fun generateDisplayNameForMethod(testClass: Class<*>, testMethod: Method): String =
        testMethod.name + "."

    open fun generateDisplayNameForPuzzle(puzzle: PuzzleData<*, *>): String {
        val origin = puzzle.origin
        val header = if (origin.isNotBlank()) "[$origin] " else ""

        return header + puzzle.name
    }
}

private object PuzzleLookup {
    private val ROOT: Package = this::class.java.`package`

    private val lookup: Map<String, PuzzleData<*, *>> by lazy {
        @Suppress("UNCHECKED_CAST")
        ReflectionSupport
            .findAllClassesInPackage(ROOT.name, { `class` ->
                isAnnotated(`class`, Puzzle::class.java) && Solution::class.java.isAssignableFrom(`class`)
            }, alwaysTrue())
            .associateBy({ element -> element.simpleName }, { element ->
                val puzzle = findAnnotation(element, Puzzle::class.java).get()
                val factory = PuzzleDataFactory.get(puzzle.factory)
                val solution = element.kotlin as KClass<Solution<*, *>>
                factory.create(solution)
            })
    }

    operator fun get(simpleName: String): PuzzleData<*, *>? = lookup[simpleName]
}

private const val PLURAL = "s"
private val TEST_SUFFIX = Test::class.java.simpleName + PLURAL

private fun stripTestSuffix(fixtureName: String): String =
    if (fixtureName.endsWith(TEST_SUFFIX)) fixtureName.dropLast(TEST_SUFFIX.length) else fixtureName

private val WORD_DELIMITER = Regex("""(?<=\w)(?=[A-Z])""")

/** Separates words in a *PascamelCase* [string] using a single whitespace character (`" "`). */
private fun splitPascamelCasedWords(string: String): String = string.split(WORD_DELIMITER).joinToString(" ")

/** Returns a predicate that always returns true. */
@Suppress("NOTHING_TO_INLINE")
private inline fun <T> alwaysTrue(): (T) -> Boolean = { _ -> true }
