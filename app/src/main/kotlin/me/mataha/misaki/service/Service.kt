package me.mataha.misaki.service

import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.lookup.SolutionLookup

internal fun interface SolutionService {
    fun get(origin: String, task: String): SolutionData<*, *>?
}

internal class DefaultSolutionService(
    private val lookup: SolutionLookup, private val comparator: EqualityComparator<String>
) : SolutionService {
    override fun get(origin: String, task: String): SolutionData<*, *>? =
        lookup.get(origin) { comparator.compare(it, task) }.firstOrNull()
}
