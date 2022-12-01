package scripts

import me.mataha.gradle.util.extensions.plugins.application.useUtf8

plugins {
    application
}

application {
    mainClass.set("${group}.Main")
    useUtf8()
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Main-Class" to application.mainClass.get()
        )
    }
}
