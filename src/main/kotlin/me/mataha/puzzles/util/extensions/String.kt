package me.mataha.puzzles.util.extensions

import java.nio.charset.Charset

fun String.md5Hex(charset: Charset = Charsets.UTF_8): String = me.mataha.puzzles.util.md5Hex(this, charset)
