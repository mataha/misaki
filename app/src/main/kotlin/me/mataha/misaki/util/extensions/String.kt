package me.mataha.misaki.util.extensions

import java.nio.charset.Charset
import me.mataha.misaki.util.md5Hex as utilMd5Hex

fun String.md5Hex(charset: Charset = Charsets.UTF_8): String = utilMd5Hex(this, charset)
