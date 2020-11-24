package me.mataha.misaki.lookup

import org.koin.dsl.module

internal val module = module {
    single<SolutionLookup> {
        DefaultSolutionLookup()
    }
}
