package me.mataha.misaki.lookup

import me.mataha.misaki.ROOT
import org.koin.dsl.module

internal val module = module {
    single<SolutionLookup> {
        DefaultSolutionLookup(ROOT)
    }
}
