@file:JvmName("Resources")

package me.mataha.puzzles.util

import java.net.URL
import kotlin.reflect.KClass

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

fun resource(path: String, contextClass: KClass<*>): URL
{
    val url = contextClass.java.getResource(path)

    return requireNotNull(url)
        { "Resource '$path' relative to '$contextClass' was not found." }
}

private object Context
