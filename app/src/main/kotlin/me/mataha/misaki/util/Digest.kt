@file:JvmName("Digest")

package me.mataha.misaki.util

import me.mataha.misaki.util.extensions.hex
import java.nio.charset.Charset
import java.security.MessageDigest

private const val MD5 = "MD5"

private fun getMd5Digest(): MessageDigest = MessageDigest.getInstance(MD5)

fun md5(bytes: ByteArray): ByteArray = getMd5Digest().digest(bytes)

fun md5(string: String, charset: Charset = Charsets.UTF_8): ByteArray = md5(string.toByteArray(charset))

fun md5Hex(string: String, charset: Charset = Charsets.UTF_8): String = md5(string, charset).hex()
