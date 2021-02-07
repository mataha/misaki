package me.mataha.misaki.cli

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.config
import org.koin.dsl.module
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

private const val ENVIRONMENT_KEY = "misaki.app.cli.context.environment"

@ExperimentalTime
internal val module = module {
    single<Cli> { (runScriptName: String, version: String) ->
        DefaultCli(
            runScriptName,
            version,
            environmentFileName = getProperty(ENVIRONMENT_KEY),
            service = get(),
            runner = get(),
            factory = get()
        )
    }
    factory<HttpClientEngineFactory<*>> {
        CIO.config {
            requestTimeout = 20.seconds.toLongMilliseconds()
        }
    }
}
