@file:Suppress("unused")

package me.mataha.misaki.util.annotations

import me.mataha.misaki.util.annotations.VisibleForTesting.Visibility
import kotlin.annotation.AnnotationRetention.SOURCE

/**
 * Indicates that the element annotated by this annotation (typically
 * a class, function or field) has its visibility elevated so that unit
 * tests can access it. This means that that API is not public.
 *
 * This annotation is **not** backed by any annotation processor -
 * its intended use is to be a guideline.
 *
 * Inspired by similar annotation in Android Core (licensed under ASL 2.0).
 */
@MustBeDocumented
@Retention(SOURCE)
annotation class VisibleForTesting(
    /**
     * The visibility the annotated element would have if it did not need
     * to be made visible for testing. Defaults to [Visibility.PRIVATE].
     */
    val visibility: Visibility = Visibility.PRIVATE,

    /**
     * Name of the test module. Defaults to `test` if not specified.
     */
    val testModule: String = "test"
) {
    /**
     * The visibility the annotated element would have if it did not need
     * to be made visible for testing.
     */
    enum class Visibility {
        /** Private visibility */
        PRIVATE,

        /** Protected visibility */
        PROTECTED,

        /** Internal visibility */
        INTERNAL,

        /** Not visible at all (should be called only from tests) */
        NONE
    }
}
