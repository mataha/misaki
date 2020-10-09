package me.mataha.puzzles.adventofcode.aoc2015.day06

data class Instruction(val type: Type, val x: IntRange, val y: IntRange)
{
    enum class Type
    {
        TURN_ON, TURN_OFF, TOGGLE
    }
}
