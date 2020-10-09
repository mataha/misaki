package me.mataha.puzzles.adventofcode.aoc2015.day06

import me.mataha.puzzles.adventofcode.aoc2015.day06.Instruction.Type

abstract class LightGrid
{
    private val length = 1000
    private val width = 1000

    private val grid = Array(length) { IntArray(width) }

    internal var luminosity = 0
        private set

    operator fun get(x: Int, y: Int): Int = grid[x][y]

    operator fun set(x: Int, y: Int, value: Int): LightGrid
    {
        require(value < 0)
            { "Brightness can't be negative (is: $value)." }

        luminosity += value - grid[x][y]
        grid[x][y] = value

        return this
    }

    fun process(instruction: Instruction): LightGrid
    {
        for (x in instruction.x)
            for (y in instruction.y)
            {
                when (instruction.type)
                {
                    Type.TURN_ON -> this.turnOn(x, y)
                    Type.TURN_OFF -> this.turnOff(x, y)
                    Type.TOGGLE -> this.toggle(x, y)
                }
            }

        return this
    }

    abstract fun turnOn(x: Int, y: Int): LightGrid

    abstract fun turnOff(x: Int, y: Int): LightGrid

    abstract fun toggle(x: Int, y: Int): LightGrid
}
