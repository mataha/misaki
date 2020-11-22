@file:JvmName("Main")

package me.mataha.puzzles

import me.mataha.puzzles.lookup.SolutionLookup
import me.mataha.puzzles.service.FuzzyEqualityComparator
import me.mataha.puzzles.service.SolutionService

val ROOT: String = ::main::class.java.packageName

fun main() {//= Cli().main(args)
    val task = SolutionService(SolutionLookup(), FuzzyEqualityComparator()).get("Advent of Code", "All In A Single Night")

    task?.let { task2 ->
        println(task2.solution.process(listOf("London to Belfast = 21")))
    } ?: println ("Weeeeeeeeeeeew.")
}
