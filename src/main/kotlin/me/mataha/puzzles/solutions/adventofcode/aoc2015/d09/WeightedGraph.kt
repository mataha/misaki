package me.mataha.puzzles.solutions.adventofcode.aoc2015.d09

class WeightedGraph<Node> {
    private val edges: MutableMap<Node, MutableMap<Node, Int>> = mutableMapOf()

    private val nodes: MutableSet<Node>
        get() = edges.keys

    private operator fun get(source: Node, destination: Node): Int =
        edges.getValue(source).getValue(destination)

    operator fun set(source: Node, destination: Node, weight: Int): WeightedGraph<Node> {
        require(weight > 0) {
            "Weight must be a positive number (is: $weight)."
        }

        edges.getOrPut(source, ::mutableMapOf)[destination] = weight
        edges.getOrPut(destination, ::mutableMapOf)[source] = weight

        return this
    }

    fun travel(aggregate: Iterable<Int>.() -> Int?): Int =
        nodes
            .map { node -> travel(aggregate, node, nodes - node) }
            .aggregate() ?: 0

    private fun travel(aggregate: Iterable<Int>.() -> Int?, start: Node, remaining: Set<Node>): Int =
        remaining
            .map { node -> this[node, start] + travel(aggregate, node, remaining - node) }
            .aggregate() ?: 0
}
