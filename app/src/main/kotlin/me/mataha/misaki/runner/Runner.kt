package me.mataha.misaki.runner

import me.mataha.misaki.domain.process
import me.mataha.misaki.service.SolutionService
import kotlin.time.measureTimedValue

internal fun interface SolutionRunner {
    fun run(origin: String, task: String, input: String): Result?
}

internal class DefaultSolutionRunner(
    private val service: SolutionService, private val skinner: Skinner<String>
) : SolutionRunner {
    override fun run(origin: String, task: String, input: String): Result? {
        val data = service.get(origin, task)
        val skinned = skinner.skin(input)

        return data?.let { solutionData ->
            val timed = measureTimedValue {
                solutionData.solution.process(skinned)
            }

            Result(data.name, timed.value, timed.duration)
        }
    }
}
