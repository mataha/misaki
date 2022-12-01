package me.mataha.misaki.util

import me.mataha.misaki.util.extensions.hex
import java.nio.charset.Charset
import java.security.MessageDigest

private const val MD5_ALGORITHM = "MD5"

fun md5(bytes: ByteArray): ByteArray = MessageDigest.getInstance(MD5_ALGORITHM).digest(bytes)

fun md5(string: String, charset: Charset = Charsets.UTF_8): ByteArray = md5(string.toByteArray(charset))

fun md5Hex(string: String, charset: Charset = Charsets.UTF_8): String = md5(string, charset).hex()
