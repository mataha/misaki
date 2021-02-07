object Kotlin {
    const val stdlib = "1.4.21"
    const val coroutines = "1.4.2"
}

object BuildPlugins {
    object Versions {
        const val exhaustive = "0.1.1"
    }
}

object Libraries {
    private object Versions {
        const val betterParse = "0.4.1"
        const val classgraph = "4.8.90"
        const val clikt = "3.1.0"
        const val koin = "2.2.2"
        const val ktorClientCio = "1.4.3"
    }

    const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"

    const val BETTER_PARSE = "com.github.h0tk3y.betterParse:better-parse:${Versions.betterParse}"
    const val CLASSGRAPH = "io.github.classgraph:classgraph:${Versions.classgraph}"
    const val CLIKT = "com.github.ajalt.clikt:clikt:${Versions.clikt}"
    const val KOIN = "org.koin:koin-core:${Versions.koin}"
    const val KTOR_CLIENT_CIO = "io.ktor:ktor-client-cio:${Versions.ktorClientCio}"
}

object TestLibraries {
    private object Versions {
        const val junit5 = "5.7.0"
        const val kotestAssertions = "4.3.1"
    }

    const val JUNIT = "org.junit:junit-bom:${Versions.junit5}"

    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:${Versions.kotestAssertions}"
}
