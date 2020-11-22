@file:JvmName("Resources")

package me.mataha.misaki.util

import java.net.URL

/**
 * Returns a `URL` pointing to a resource under the specified [path].
 *
 * Throws an [IllegalArgumentException] if the resource was not found.
 */
fun resource(path: String): URL {
    val loader = listOfNotNull(
        Thread.currentThread().contextClassLoader,
        ::resource::class.java.classLoader,
    ).first()

    val url = loader.getResource(path)

    return requireNotNull(url) {
        "Resource '$path' was not found."
    }
}
