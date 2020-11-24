package me.mataha.misaki.service

import org.koin.dsl.module

private const val THRESHOLD_KEY = "misaki.app.service.fuzzy_comparer.threshold"

internal val module = module {
    single<EqualityComparator<String>> {
        FuzzyEqualityComparator(getProperty(THRESHOLD_KEY).toInt())
    }
    single<SolutionService> {
        DefaultSolutionService(get(), get())
    }
}
