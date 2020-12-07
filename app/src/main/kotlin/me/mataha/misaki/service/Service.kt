package me.mataha.misaki.service

import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.repository.PuzzleRepository

internal fun interface PuzzleService {
    fun get(origin: String, task: String): PuzzleData<*, *>?
}

internal class DefaultPuzzleService(
    private val repository: PuzzleRepository, private val comparator: EqualityComparator<String>
) : PuzzleService {
    override fun get(origin: String, task: String): PuzzleData<*, *>? =
        repository.get(origin) { comparator.compare(it, task) }.firstOrNull()
}
