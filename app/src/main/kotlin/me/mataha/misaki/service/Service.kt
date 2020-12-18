package me.mataha.misaki.service

import me.mataha.misaki.domain.PuzzleData
import me.mataha.misaki.repository.PuzzleRepository

internal interface PuzzleService {
    fun get(origin: String, task: String): PuzzleData<*, *>

    fun getOrNull(origin: String, task: String): PuzzleData<*, *>?
}

internal class DefaultPuzzleService(
    private val repository: PuzzleRepository, private val comparator: EqualityComparator<String>
) : PuzzleService {
    override fun get(origin: String, task: String): PuzzleData<*, *> =
        getOrNull(origin, task) ?: throw PuzzleNotFoundException("$origin: '$task' was not found")

    override fun getOrNull(origin: String, task: String): PuzzleData<*, *>? =
        repository.get(origin) { taskName -> comparator.compare(task, taskName) }.firstOrNull()
}

internal class PuzzleNotFoundException(message: String?) : NoSuchElementException(message)
