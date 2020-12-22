package me.mataha.misaki.solutions.adventofcode.aoc2015.d16

class Memoir(private val compounds: Map<String, Int>, internal val aunt: Sue? = null) {
    internal constructor(vararg compounds: Pair<String, Int>) : this(mapOf(*compounds))

    internal inline fun compareTo(other: Memoir, comparator: CompoundComparator): Boolean =
        this.compounds.all { compound ->
            comparator(compound.value, other.compounds.getValue(compound.key), compound.key)
        }
}

internal typealias CompoundComparator = (a: Int, b: Int, key: String) -> Boolean
