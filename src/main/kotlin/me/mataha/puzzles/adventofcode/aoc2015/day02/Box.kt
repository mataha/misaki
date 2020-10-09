package me.mataha.puzzles.adventofcode.aoc2015.day02

import me.mataha.puzzles.util.max

data class Box(val length: Int, val width: Int, val height: Int)
{
    val smallestSideArea: Int
        get() = length * width * height / max(length, width, height)

    val surfaceArea: Int
        get() = 2 * (length * width + width * height + height * length)

    val smallestSidePerimeter: Int
        get() = 2 * (length + width + height - max(length, width, height))

    val volume: Int
        get() = length * width * height

    companion object
    {
        fun create(dimensions: List<Int>): Box
        {
            require(dimensions.size == NUMBER_OF_DIMENSIONS)
                { "Number of dimensions does not match (is: ${dimensions.size})." }

            return create(dimensions[0], dimensions[1], dimensions[2])
        }

        fun create(length: Int, width: Int, height: Int): Box
        {
            require(length > 0 && width > 0 && height > 0)
                { "Dimensions must be positive numbers (are: [$length, $width, $height])." }

            return Box(length, width, height)
        }

        private const val NUMBER_OF_DIMENSIONS = 3
    }
}
