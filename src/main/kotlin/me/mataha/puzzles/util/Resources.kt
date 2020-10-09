@file:JvmName("Resources")

package me.mataha.puzzles.util

import java.net.URL

fun resource(path: String): URL
{
    val loader = listOfNotNull(
        Thread.currentThread().contextClassLoader,
        Context.javaClass.classLoader,
    ).first()

    val url = loader.getResource(path)

    return requireNotNull(url)
        { "Resource '$path' was not found." }
}

private object Context
