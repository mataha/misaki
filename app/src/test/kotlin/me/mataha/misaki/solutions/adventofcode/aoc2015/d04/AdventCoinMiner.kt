@file:JvmName("AdventCoinMiner")

package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

private const val KEY = "a"
private val PREFIX = "0".repeat(5)

@ExperimentalTime
private val TIMEOUT = 1.seconds

@ExperimentalTime
internal fun main(vararg args: String) {
    val iterations = parse {
        args[0].toInt()
            .also { require(it > 0) }
    }

    var key = parse {
        args.getOrElse(1) { KEY }
            .also { require(it.matches(Regex("""[a-z]+"""))) }
    }

    val prefix = parse {
        args.getOrElse(2) { PREFIX }
            .also { require(it.matches(Regex("""[0-9a-f]+"""))) }
    }

    var anything = false

    runBlocking(Dispatchers.Default) {
        repeat(iterations) {
            val result = async {
                solve(key, prefix)
            }

            withTimeoutOrNull(TIMEOUT) {
                println("Key: $key, number: ${result.await()}")
                if (!anything) anything = true
            }

            key++
        }
    }

    if (!anything) {
        println("Could not find any answers under $TIMEOUT timeout.")
    }
}

private val solve = ::findLowestMd5StartingWith

private operator fun String.inc(): String {
    for ((index, char) in this.reversed().withIndex()) {
        if (char != 'z') {
            return take(length - (index + 1)) + char.inc() + "a".repeat(index)
        }
    }

    return "a".repeat(length + 1)
}

private inline fun <T> parse(crossinline block: () -> T): T = try {
    block()
} catch (_: Exception) {
    usage()
}

private fun usage(): Nothing {
    println("Usage: AdventCoinMiner ITERATIONS [KEY=$KEY] [PREFIX=$PREFIX]")
    exitProcess(1)
}
