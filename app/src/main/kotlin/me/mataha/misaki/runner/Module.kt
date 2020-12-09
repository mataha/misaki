package me.mataha.misaki.runner

import org.koin.dsl.module

internal val module = module {
    single<Trimmer<String>> {
        Trimmer { string -> string.trim() }
    }
    factory<SolutionRunner> {
        DefaultSolutionRunner(get())
    }
}
