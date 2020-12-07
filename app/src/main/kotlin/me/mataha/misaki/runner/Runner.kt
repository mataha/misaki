package me.mataha.misaki.runner

import me.mataha.misaki.domain.process
import me.mataha.misaki.service.PuzzleService
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

internal fun interface SolutionRunner {
    fun run(origin: String, task: String, input: String): Result?
}

internal class DefaultSolutionRunner(
    private val service: PuzzleService, private val skinner: Skinner<String>
) : SolutionRunner {
    @ExperimentalTime
    override fun run(origin: String, task: String, input: String): Result? {
        val puzzle = service.get(origin, task)
        val skinned = skinner.skin(input)

        return puzzle?.let { data ->
            val timed = measureTimedValue {
                data.solution.process(skinned)
            }

            Result(puzzle.name, timed.value, timed.duration)
        }
    }
}
