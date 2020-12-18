package me.mataha.misaki

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import org.koin.core.context.startKoin
import org.koin.dsl.module
import me.mataha.misaki.repository.module as repositoryModule
import me.mataha.misaki.runner.module as runnerModule
import me.mataha.misaki.service.module as serviceModule

private val module = module {
    single<HttpClientEngineFactory<*>> { CIO }
}

internal val koin = startKoin {
    fileProperties()
    modules(module, repositoryModule, runnerModule, serviceModule)
}.koin
