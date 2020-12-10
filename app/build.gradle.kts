import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.20"
}

application {
    mainClass.value("me.mataha.misaki.Main")
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    implementation("com.github.ajalt.clikt:clikt:3.0.1")
    implementation("com.github.h0tk3y.betterParse:better-parse-jvm:0.4.0-alpha-3")
    implementation("io.github.classgraph:classgraph:4.8.90")
    implementation("org.koin:koin-core:2.2.1")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Version" to project.version
        )
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events(SKIPPED, FAILED)
    }
}

@Suppress("SuspiciousCollectionReassignment")
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-java-parameters"
        freeCompilerArgs += "-Xopt-in=kotlin.ExperimentalUnsignedTypes"
        freeCompilerArgs += "-Xopt-in=kotlin.contracts.ExperimentalContracts"
        freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime"
    }
}
