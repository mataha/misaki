package me.mataha.misaki.runner

import me.mataha.misaki.domain.Solution
import me.mataha.misaki.domain.process
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

internal fun interface SolutionRunner {
    fun run(solution: Solution<*, *>, input: String): Result
}

internal class DefaultSolutionRunner(private val trimmer: Trimmer<String>) : SolutionRunner {
    @ExperimentalTime
    override fun run(solution: Solution<*, *>, input: String): Result {
        val trimmed = trimmer.trim(input)

        return measureTimedValue {
            solution.process(trimmed)
        }
    }
}
