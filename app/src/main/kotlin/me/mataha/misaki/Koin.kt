package me.mataha.misaki

import org.koin.core.context.startKoin
import me.mataha.misaki.repository.module as repositoryModule
import me.mataha.misaki.runner.module as runnerModule
import me.mataha.misaki.service.module as serviceModule

internal val koin = startKoin {
    fileProperties()
    modules(repositoryModule, runnerModule, serviceModule)
}.koin
