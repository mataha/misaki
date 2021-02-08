import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Kotlin.stdlib
    id("app.cash.exhaustive") version BuildPlugins.Versions.exhaustive
    id("scripts.misaki-application")
}

application {
    mainClass.set("me.mataha.misaki.Main")
}

java {
    sourceCompatibility = Compatibility.JAVA_VERSION
    targetCompatibility = Compatibility.JAVA_VERSION
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(Libraries.kotlinCoroutines)

    implementation(Libraries.betterParse)
    implementation(Libraries.classgraph)
    implementation(Libraries.clikt)
    implementation(Libraries.koin)
    implementation(Libraries.ktorClientCio)

    testImplementation(platform(TestLibraries.junit5))
    testImplementation(TestLibraries.junitJupiter)
    testImplementation(TestLibraries.kotestAssertions)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(SKIPPED, FAILED)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = Target.BYTECODE_VERSION
        freeCompilerArgs = listOf(
            "-java-parameters",
            "-Xinline-classes",
            "-Xopt-in=kotlin.ExperimentalUnsignedTypes",
            "-Xopt-in=kotlin.contracts.ExperimentalContracts",
            "-Xopt-in=kotlin.io.path.ExperimentalPathApi",
            "-Xopt-in=kotlin.time.ExperimentalTime"
        )
    }
}
