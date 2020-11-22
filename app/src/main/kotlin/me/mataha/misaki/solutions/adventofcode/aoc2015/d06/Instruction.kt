package me.mataha.misaki.solutions.adventofcode.aoc2015.d06

data class Instruction(val type: Type, val x: IntRange, val y: IntRange) {
    enum class Type {
        TURN_ON, TURN_OFF, TOGGLE
    }
}
