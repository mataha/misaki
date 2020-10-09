package me.mataha.puzzles

import io.github.classgraph.ClassGraph
import me.mataha.puzzles.domain.Puzzle
import kotlin.time.ExperimentalTime

private fun getThisPackageName(): String = ::getThisPackageName.javaClass.packageName

/*
 *  Goals:
 *    * auto-discovery of solutions to puzzles
 *    * some basic parsing of input
 */
@ExperimentalTime
fun main(@Suppress("UNUSED_PARAMETER") vararg args: String)
{
    ClassGraph()
        .enableAllInfo()
        .acceptPackages(getThisPackageName())
        .scan()
        .use { result ->
            result.getClassesWithAnnotation(Puzzle::class.java.name)
                .filter { !it.isAnnotation }
        }
}

// Test cases for #04:
// abcdef -- 609043, 6742839
// pqrstuv -- 1048970, 5714438
