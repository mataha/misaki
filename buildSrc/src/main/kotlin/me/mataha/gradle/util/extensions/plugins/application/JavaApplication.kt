package me.mataha.gradle.util.extensions.plugins.application

import org.gradle.api.plugins.JavaApplication
import java.nio.charset.Charset

private fun JavaApplication.useFileEncoding(charset: Charset) {
    applicationDefaultJvmArgs += "-Dfile.encoding=${charset}"
}

fun JavaApplication.useUtf8() = this.useFileEncoding(Charsets.UTF_8)
