package me.mataha.misaki

import java.nio.file.LinkOption
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

@ExperimentalPathApi
private val path = Path(".")

@ExperimentalPathApi
internal fun runScriptName(default: String = "run"): String {
    val files = path
        .listDirectoryEntries()
        .filter { entry -> entry.isRegularFile(LinkOption.NOFOLLOW_LINKS) }

    val names = files
        .filter { file -> !file.isGradle() }
        .map { file -> file.nameWithoutExtension }
        .filter { name -> name.isNotBlank() } // dotfiles

    return names.firstOrNull { name -> name in names - name } ?: default
}

private fun Path.isGradle(): Boolean = "gradle" in this.name
