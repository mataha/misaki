@file:JvmName("Main") // Has to be hardcoded - consts won't work

package me.mataha.misaki

import java.nio.file.LinkOption.NOFOLLOW_LINKS
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

fun main(vararg args: String) = Cli(getRunScriptName()).main(args)

internal val root: Package = ::main::class.java.`package`

@ExperimentalPathApi
private val path: Path = Path(".")

@ExperimentalPathApi
private fun getRunScriptName(default: String = ::main.name.capitalize()): String {
    val files = path
        .listDirectoryEntries()
        .filter { entry -> entry.isRegularFile(NOFOLLOW_LINKS) }

    val names = files
        .filter { file -> !file.isGradle() }
        .map { file -> file.nameWithoutExtension }
        .filter { name -> name.isNotBlank() } // dotfiles

    return names.firstOrNull { name -> name in names - name } ?: default
}

private fun Path.isGradle(): Boolean = "gradle" in this.name
