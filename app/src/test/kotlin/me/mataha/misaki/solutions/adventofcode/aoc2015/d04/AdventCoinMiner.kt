@file:JvmName("AdventCoinMiner")

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

    runBlocking {
        repeat(iterations) {
            val job = async(Dispatchers.Default) {
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

private const val HASH_NOT_FOUND = -1

private suspend fun mine(key: String, prefix: String): Int = coroutineScope {
    withContext(coroutineContext) {
        repeat(Int.MAX_VALUE) { iteration ->
            @Exhaustive when (isActive) {
                true -> {
                    val number = iteration + 1
                    val string = key + number

                    if (string.md5Hex().startsWith(prefix)) {
                        return@withContext number
                    }
                }
                false -> return@withContext HASH_NOT_FOUND
            }
        }

        HASH_NOT_FOUND
    }
}

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
