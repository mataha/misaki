package me.mataha.misaki.util.extensions

fun ByteArray.hex(): String = this.joinToString("") { byte -> "%02x".format(byte) }
