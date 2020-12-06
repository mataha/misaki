@file:Suppress("NOTHING_TO_INLINE")

package me.mataha.misaki.util.extensions

import me.mataha.misaki.util.internal.annotations.InlineCall
import java.io.OutputStream
import java.io.PrintWriter
import java.nio.charset.Charset

@InlineCall
inline fun OutputStream.printWriter(charset: Charset = Charsets.UTF_8, autoFlush: Boolean = false): PrintWriter =
    PrintWriter(this.bufferedWriter(charset), autoFlush)
