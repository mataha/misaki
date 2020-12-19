package me.mataha.misaki.util.extensions

/** Returns a short description of this throwable in a compact form. */
fun Throwable.toCompactString(): String {
    val name = this::class.simpleName
    val localized = this.localizedMessage

    return name + if (localized != null) ": $localized" else ""
}
