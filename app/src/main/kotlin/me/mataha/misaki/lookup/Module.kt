package me.mataha.misaki.lookup

import me.mataha.misaki.root
import org.koin.dsl.module

internal val module = module {
    single<SolutionLookup> {
        DefaultSolutionLookup(root.name)
    }
}
