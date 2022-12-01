@file:JvmName("AdventCoinMiner") // -Dkotlinx.coroutines.debug

package me.mataha.misaki.solutions.adventofcode.aoc2015.d04

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import me.mataha.misaki.util.extensions.md5Hex
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds

private const val KEY = "a"
private const val PREFIX = "00000" // "0".repeat(5)

private val TIMEOUT = 1.seconds

private fun usage(): Nothing {
    println("Usage: AdventCoinMiner ITERATIONS [KEY=$KEY] [PREFIX=$PREFIX]")
    exitProcess(1)
}

internal fun main(vararg args: String) = runBlocking {
    val iterations = parse {
        args.first().toInt()
            .also { arg -> require(arg > 0) }
    }

    var key = parse {
        args.getOrElse(1) { KEY }
            .also { arg -> require(arg matches Regex("""[a-z]+""")) }
    }

    val prefix = parse {
        args.getOrElse(2) { PREFIX }
            .also { arg -> require(arg matches Regex("""[0-9a-f]+""")) }
    }

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

private inline fun <T> parse(crossinline block: () -> T): T = try {
    block()
} catch (_: Exception) {
    usage()
}

private suspend fun mine(key: String, prefix: String): Int = withContext(Dispatchers.Default) {
    repeat(Int.MAX_VALUE) { index ->
        val number = index + 1

        when (isActive) {
            true -> {
                val string = key + number

                if (string.md5Hex().startsWith(prefix)) {
                    return@withContext number
                }
            }
            false -> {
                throw CancellationException("Mining has been cancelled (on $number)")
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
