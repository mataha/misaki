package me.mataha.misaki.solutions.adventofcode.aoc2015.d09

class WeightedGraph<N> {
    private val edges: MutableMap<N, MutableMap<N, Int>> by lazy { mutableMapOf() }

    private val nodes: MutableSet<N>
        get() = edges.keys

    private operator fun get(source: N, destination: N): Int =
        edges.getValue(source).getValue(destination)

    internal operator fun set(source: N, destination: N, weight: Int): WeightedGraph<N> {
        require(weight > 0) {
            "Weight must be a positive number (is: $weight)"
        }

        edges.getOrPut(source, ::mutableMapOf)[destination] = weight
        edges.getOrPut(destination, ::mutableMapOf)[source] = weight

        return this
    }

    internal fun traverse(aggregate: Iterable<Int>.() -> Int?): Int {
        fun traverse(aggregate: Iterable<Int>.() -> Int?, start: N, remaining: Set<N>): Int {
            return remaining
                .map { node -> this[node, start] + traverse(aggregate, node, remaining - node) }
                .aggregate() ?: 0
        }

        return nodes
            .map { node -> traverse(aggregate, node, nodes - node) }
            .aggregate() ?: 0
    }
}
