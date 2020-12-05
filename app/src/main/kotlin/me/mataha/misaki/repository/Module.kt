package me.mataha.misaki.repository

import me.mataha.misaki.root
import org.koin.dsl.module

internal val module = module {
    single<SolutionRepository> {
        DefaultSolutionRepository(root.name)
    }
}
