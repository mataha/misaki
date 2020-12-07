package me.mataha.misaki.runner

import org.koin.dsl.module

internal val module = module {
    single<Skinner<String>> {
        Skinner { string -> string.trim() }
    }
    factory<SolutionRunner> {
        DefaultSolutionRunner(get())
    }
}
