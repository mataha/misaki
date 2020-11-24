package me.mataha.misaki

import org.koin.core.context.startKoin
import me.mataha.misaki.lookup.module as lookupModule
import me.mataha.misaki.service.module as serviceModule

internal val koin = startKoin {
    fileProperties()
    modules(lookupModule, serviceModule)
}.koin
