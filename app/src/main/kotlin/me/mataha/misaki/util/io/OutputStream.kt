package me.mataha.misaki.util.io

import java.io.OutputStream
import java.io.PrintWriter
import java.nio.charset.Charset

fun OutputStream.printWriter(charset: Charset = Charsets.UTF_8, autoFlush: Boolean = false): PrintWriter =
    PrintWriter(this.bufferedWriter(charset), autoFlush)
