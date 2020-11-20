package me.mataha.puzzles.util.extensions

import java.nio.charset.Charset
import me.mataha.puzzles.util.md5Hex as utilMd5Hex

fun String.md5Hex(charset: Charset = Charsets.UTF_8): String = utilMd5Hex(this, charset)
