package me.mataha.puzzles.service

import me.mataha.puzzles.domain.SolutionData
import me.mataha.puzzles.lookup.ISolutionLookup

interface ISolutionService {
    fun get(origin: String, task: String): SolutionData<*, *>?
}

class SolutionService(
    private val lookup: ISolutionLookup,
    private val comparator: IEqualityComparator<String>
) : ISolutionService {
    override fun get(origin: String, task: String): SolutionData<*, *>? =
        lookup.get(origin) { string -> comparator.compare(string, task) }.firstOrNull()
}
