import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.0"
}

group = "me.mataha"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "me.mataha.puzzles.MainKt"
}

repositories {
    jcenter()

    mavenCentral()
    maven {
        setUrl("https://dl.bintray.com/hotkeytlt/maven")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    implementation("com.andreapivetta.kolor:kolor:1.0.0")
    implementation("com.github.h0tk3y.betterParse:better-parse-jvm:0.4.0-alpha-3")
    implementation("io.github.classgraph:classgraph:4.8.90")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events(PASSED, SKIPPED, FAILED)
    }
}

@Suppress("SuspiciousCollectionReassignment")
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-java-parameters"
        freeCompilerArgs += "-Xopt-in=kotlin.ExperimentalUnsignedTypes"
        freeCompilerArgs += "-Xopt-in=kotlin.contracts.ExperimentalContracts"
    }
}
