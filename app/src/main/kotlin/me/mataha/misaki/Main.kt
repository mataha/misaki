@file:JvmName("Main") // Has to be hardcoded - consts won't work

package me.mataha.misaki

import java.io.File
import java.nio.file.Paths

fun main(vararg args: String) = Cli(getRunScriptName()).main(args)

internal val root: Package = ::main::class.java.`package`

// TODO: Use Kotlin Path API as soon as it's available
private fun getRunScriptName(default: String = ::main.name.capitalize()): String {
    val files = Paths.get(".")
        .toFile()
        .listFiles()!!
        .filter { file -> file.isFile }

    val names = files
        .filter { file -> !file.isGradleFile() }
        .map { file -> file.nameWithoutExtension }
        .filter { name -> name.isNotBlank() } // dotfiles

    return names.firstOrNull { name -> name in names - name } ?: default
}

private fun File.isGradleFile(): Boolean = "gradle" in this.name
