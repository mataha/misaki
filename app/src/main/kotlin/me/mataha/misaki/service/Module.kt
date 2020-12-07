package me.mataha.misaki.service

import me.mataha.misaki.util.text.levenshteinDistance
import org.koin.dsl.module

private const val THRESHOLD_KEY = "misaki.app.service.comparator.threshold"

internal val module = module {
    single<EqualityComparator<String>> {
        EqualityComparator { a, b ->
            levenshteinDistance(
                a.toLowerCase(),
                b.toLowerCase()
            ) <= getProperty(THRESHOLD_KEY).toInt()
        }
    }
    single<PuzzleService> {
        DefaultPuzzleService(get(), get())
    }
}
