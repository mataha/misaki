package me.mataha.misaki

import java.nio.file.LinkOption
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

private val path = Path(".")

internal fun runScriptName(default: String = "run"): String {
    val files = path
        .listDirectoryEntries()
        .filter { entry -> entry.isRegularFile(LinkOption.NOFOLLOW_LINKS) }

    val names = files
        .filter { file -> "gradle" !in file.name } // exclude gradle files
        .map { file -> file.nameWithoutExtension }
        .filter { name -> name.isNotBlank() } // exclude dots and dotfiles

    // We're interested in a script that has at least two versions
    return names.firstOrNull { name -> name in names - name } ?: default
}
