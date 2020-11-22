package me.mataha.puzzles.solutions.adventofcode.aoc2015.d06

import me.mataha.puzzles.solutions.adventofcode.aoc2015.d06.Instruction.Type

internal abstract class LightGrid {
    private val length = DEFAULT_LENGTH
    private val width = DEFAULT_WIDTH
    private val grid = Array(length) { IntArray(width) }

    internal var luminosity = 0
        private set

    protected operator fun get(x: Int, y: Int): Int = grid[x][y]

    protected operator fun set(x: Int, y: Int, value: Int): LightGrid {
        require(value >= 0) {
            "Brightness can't be negative (is: $value)."
        }

        luminosity += value - grid[x][y]
        grid[x][y] = value

        return this
    }

    internal fun process(instruction: Instruction): LightGrid {
        for (x in instruction.x)
            for (y in instruction.y) {
                when (instruction.type) {
                    Type.TURN_ON -> this.turnOn(x, y)
                    Type.TURN_OFF -> this.turnOff(x, y)
                    Type.TOGGLE -> this.toggle(x, y)
                }
            }

        return this
    }

    protected abstract fun turnOn(x: Int, y: Int): LightGrid

    protected abstract fun turnOff(x: Int, y: Int): LightGrid

    protected abstract fun toggle(x: Int, y: Int): LightGrid

    private companion object {
        private const val DEFAULT_LENGTH = 1000
        private const val DEFAULT_WIDTH = 1000
    }
}
