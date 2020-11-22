package me.mataha.misaki.service

import me.mataha.misaki.domain.SolutionData
import me.mataha.misaki.lookup.ISolutionLookup

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
