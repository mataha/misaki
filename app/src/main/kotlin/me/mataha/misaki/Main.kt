@file:JvmName("Main")

package me.mataha.misaki

import me.mataha.misaki.service.SolutionService

val ROOT: String = ::main::class.java.`package`.name

fun main() {
    val service = koin.get<SolutionService>()

    val task = service.get("Advent of Code", "All In A Single Night")

    task?.let {
        println(it.solution.process(listOf("London to Belfast = 21")))
    } ?: println ("Weeeeeeeeeeeew.")
}
