import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Kotlin.stdlib
    id("scripts.misaki-application")
}

java {
    val compatibility = Compatibility.JAVA_VERSION

    sourceCompatibility = compatibility
    targetCompatibility = compatibility
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

        val experimentalClasses = arrayOf(
            "kotlin.contracts.ExperimentalContracts",
            "kotlin.time.ExperimentalTime"
        )

        freeCompilerArgs = listOf(
            *experimentalClasses.map { cls -> "-opt-in=${cls}" }.toTypedArray(),
            "-java-parameters"
        )
    }
}
