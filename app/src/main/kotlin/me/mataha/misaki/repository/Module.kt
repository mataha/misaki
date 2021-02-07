package me.mataha.misaki.repository

import org.koin.dsl.module

internal val module = module {
    single<PuzzleRepository> {
        DefaultPuzzleRepository()
    }
}
