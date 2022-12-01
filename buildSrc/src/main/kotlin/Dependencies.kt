object Kotlin {
    const val stdlib = "1.7.21"
    const val coroutines = "1.6.4"
}

object Libraries {
    private object Versions {
        const val betterParse = "0.4.1"
        const val classgraph = "4.8.90"
        const val clikt = "3.1.0"
        const val koin = "3.2.2"
        const val ktorClientCio = "1.4.3"
    }

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"

    const val betterParse = "com.github.h0tk3y.betterParse:better-parse:${Versions.betterParse}"
    const val classgraph = "io.github.classgraph:classgraph:${Versions.classgraph}"
    const val clikt = "com.github.ajalt.clikt:clikt:${Versions.clikt}"
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val ktorClientCio = "io.ktor:ktor-client-cio:${Versions.ktorClientCio}"
}

object TestLibraries {
    private object Versions {
        const val junit5 = "5.7.0"
        const val kotestAssertions = "4.3.1"
    }

    const val junit5 = "org.junit:junit-bom:${Versions.junit5}"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"

    const val kotestAssertions = "io.kotest:kotest-assertions-core:${Versions.kotestAssertions}"
}
