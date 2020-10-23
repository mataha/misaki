package me.mataha.puzzles.solutions.adventofcode.aoc2015.d03

import me.mataha.puzzles.domain.OneLineParser
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay
import me.mataha.puzzles.util.extensions.next

@AdventOfCode("Perfectly Spherical Houses in a Vacuum", 2015, 3)
class PerfectlySphericalHouses: AdventOfCodeDay<String, Int>(), OneLineParser
{
    override fun first(input: String): Int
    {
        var current = Position(0, 0)
        val grid = mutableSetOf(current)

        for (direction in input)
        {
            val new = current.next(direction)

            grid.add(new)
            current = new
        }

        return grid.size
    }

    override fun second(input: String): Int
    {
        val map = mutableMapOf(Turn.SANTA to Position(0, 0), Turn.ROBOT to Position(0, 0))
        var turn = Turn.SANTA
        val grid = mutableSetOf(map[turn]!!)

        for (direction in input)
        {
            val current = map[turn]!!
            val new = current.next(direction)

            grid.add(new)
            map[turn] = new

            turn = if (direction.isDirection()) turn.next() else turn
        }

        return grid.size
    }
}

private enum class Turn
{
    SANTA, ROBOT
}

private data class Position(val x: Int, val y: Int)
{
    fun next(move: Char): Position
    {
        return when (move)
        {
            '^' -> Position(x, y + 1)
            'v' -> Position(x, y - 1)
            '>' -> Position(x + 1, y)
            '<' -> Position(x - 1, y)
            else -> this
        }
    }
}

private const val DIRECTIONS = "^>v<"
private fun Char.isDirection(): Boolean = this in DIRECTIONS
