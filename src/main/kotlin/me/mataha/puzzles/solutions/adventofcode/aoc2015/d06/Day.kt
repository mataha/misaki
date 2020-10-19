package me.mataha.puzzles.solutions.adventofcode.aoc2015.d06

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import me.mataha.puzzles.domain.adventofcode.AdventOfCode
import me.mataha.puzzles.domain.adventofcode.AdventOfCodeDay

@AdventOfCode("Probably a Fire Hazard", 2015, 6)
class ProbablyAFireHazard: AdventOfCodeDay<List<Instruction>, Int>()
{
    override fun parse(input: List<String>): List<Instruction>
            = input.map { line -> InstructionGrammar.parseToEnd(line) }

    override fun first(input: List<Instruction>): Int
    {
        val grid = object: LightGrid()
        {
            override fun turnOn(x: Int, y: Int): LightGrid
                    = apply { this[x, y] = 1 }

            override fun turnOff(x: Int, y: Int): LightGrid
                    = apply { this[x, y] = 0 }

            override fun toggle(x: Int, y: Int): LightGrid
                    = apply { this[x, y] = this[x, y] xor 1 }
        }

        return grid.illuminate(input)
    }

    override fun second(input: List<Instruction>): Int
    {
        val grid = object: LightGrid()
        {
            override fun turnOn(x: Int, y: Int): LightGrid
                    = apply { this[x, y]++ }

            override fun turnOff(x: Int, y: Int): LightGrid
                    = apply { if (this[x, y] > 0) this[x, y]-- }

            override fun toggle(x: Int, y: Int): LightGrid
                    = apply { this[x, y] += 2 }
        }

        return grid.illuminate(input)
    }

    companion object
    {
        private fun LightGrid.illuminate(instructions: List<Instruction>): Int
        {
            for (instruction in instructions)
            {
                this.process(instruction)
            }

            return this.luminosity
        }
    }
}
