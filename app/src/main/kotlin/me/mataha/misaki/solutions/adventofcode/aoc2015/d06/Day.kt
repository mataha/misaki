package me.mataha.misaki.solutions.adventofcode.aoc2015.d06

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.misaki.domain.adventofcode.AdventOfCode
import me.mataha.misaki.domain.adventofcode.AdventOfCodeDay

/** See the puzzle's full description [here](https://adventofcode.com/2015/day/6). */
@AdventOfCode("Probably a Fire Hazard", 2015, 6)
class ProbablyAFireHazard : AdventOfCodeDay<List<Instruction>, Int>() {
    override fun parse(input: String): List<Instruction> =
        LightGridGrammar.parseToEnd(input)

    override fun solvePartOne(input: List<Instruction>): Int {
        val grid = object : LightGrid() {
            override fun turnOn(x: Int, y: Int) = run { this[x, y] = 1 }

            override fun turnOff(x: Int, y: Int) = run { this[x, y] = 0 }

            override fun toggle(x: Int, y: Int) = run { this[x, y] = this[x, y] xor 1 }
        }

        return grid.illuminate(input)
    }

    override fun solvePartTwo(input: List<Instruction>): Int {
        val grid = object : LightGrid() {
            override fun turnOn(x: Int, y: Int) = run { this[x, y] += 1 }

            override fun turnOff(x: Int, y: Int) = run { if (this[x, y] > 0) this[x, y] -= 1 }

            override fun toggle(x: Int, y: Int) = run { this[x, y] += 2 }
        }

        return grid.illuminate(input)
    }
}

private fun LightGrid.illuminate(instructions: List<Instruction>): Int {
    for (instruction in instructions) {
        this.process(instruction)
    }

    return this.luminosity
}
