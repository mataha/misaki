@file:JvmName("Main")

package me.mataha.misaki

import me.mataha.misaki.cli.Cli
import org.koin.core.parameter.parametersOf

fun main(vararg args: String) = koin.get<Cli> { parametersOf(runScriptName(project), version) }.main(args)

private val root: Package = ::main::class.java.`package`!!

private val project: String = root.implementationTitle ?: "app"

private val version: String = root.implementationVersion ?: "unspecified"
