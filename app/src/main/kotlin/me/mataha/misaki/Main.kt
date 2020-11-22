@file:JvmName("Main")

package me.mataha.misaki

import me.mataha.misaki.lookup.SolutionLookup
import me.mataha.misaki.service.FuzzyEqualityComparator
import me.mataha.misaki.service.SolutionService

val ROOT: String = ::main::class.java.packageName

fun main() {//= Cli().main(args)
    val task = SolutionService(SolutionLookup(), FuzzyEqualityComparator()).get("Advent of Code", "All In A Single Night")

    task?.let { task2 ->
        println(task2.solution.process(listOf("London to Belfast = 21")))
    } ?: println ("Weeeeeeeeeeeew.")
}
