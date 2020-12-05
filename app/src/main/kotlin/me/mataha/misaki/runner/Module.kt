package me.mataha.misaki.runner

import org.koin.dsl.module

internal val module = module {
    factory<SolutionRunner> {
        DefaultSolutionRunner(get(), get())
    }
    single<Skinner<String>> {
        Skinner { string -> string.trim() }
    }
}
