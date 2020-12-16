@file:JvmName("AdventCoinMiner") // -Dkotlinx.coroutines.debug

package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import app.cash.exhaustive.Exhaustive
import kotlinx.coroutines.*
import me.mataha.misaki.util.extensions.md5Hex
import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

private const val KEY = "a"
private const val PREFIX = "00000" // "0".repeat(5)

@ExperimentalTime
private val TIMEOUT = 1.seconds

@ExperimentalTime
internal fun main(vararg args: String) {
    val iterations = parse {
        args[0].toInt()
            .also { arg -> require(arg > 0) }
    }

    var key = parse {
        args.getOrElse(1) { KEY }
            .also { arg -> require(arg.matches(Regex("""[a-z]+"""))) }
    }

    val prefix = parse {
        args.getOrElse(2) { PREFIX }
            .also { arg -> require(arg.matches(Regex("""[0-9a-f]+"""))) }
    }

    runBlocking {
        repeat(iterations) {
            val job = async(CoroutineName("$prefix+$key")) {
                mine(key, prefix)
            }

            withTimeoutOrNull(TIMEOUT) {
                val number = job.await()
                println("Key: $key, number: $number")
            } ?: job.cancel()

            ++key
        }
    }
}

private suspend fun mine(key: String, prefix: String): Int = withContext(Dispatchers.Default) {
    repeat(Int.MAX_VALUE) { index ->
        @Exhaustive when (isActive) {
            true -> {
                val number = index + 1
                val string = key + number

                if (string.md5Hex().startsWith(prefix)) {
                    return@withContext number
                }
            }
            false -> {
                throw CancellationException("Mining has been cancelled")
            }
        }
    }

    HASH_NOT_FOUND
}

private const val HASH_NOT_FOUND = -1

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
