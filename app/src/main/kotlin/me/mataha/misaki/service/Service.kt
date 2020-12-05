package me.mataha.misaki.service

import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.repository.SolutionRepository

internal fun interface SolutionService {
    fun get(origin: String, task: String): SolutionData<*, *>?
}

internal class DefaultSolutionService(
    private val repository: SolutionRepository, private val comparator: EqualityComparator<String>
) : SolutionService {
    override fun get(origin: String, task: String): SolutionData<*, *>? =
        repository.get(origin) { comparator.compare(it, task) }.firstOrNull()
}
