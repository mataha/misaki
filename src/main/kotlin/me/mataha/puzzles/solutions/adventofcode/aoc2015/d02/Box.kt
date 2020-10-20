package me.mataha.puzzles.solutions.adventofcode.aoc2015.d02

import me.mataha.puzzles.util.math.max

data class Box(val length: Int, val width: Int, val height: Int)
{
    internal val smallestSideArea: Int
        get() = length * width * height / max(length, width, height)

    internal val surfaceArea: Int
        get() = 2 * (length * width + width * height + height * length)

    internal val smallestSidePerimeter: Int
        get() = 2 * (length + width + height - max(length, width, height))

    internal val volume: Int
        get() = length * width * height

    companion object
    {
        fun create(dimensions: List<Int>): Box
        {
            require(dimensions.size == NUMBER_OF_DIMENSIONS)
                { "Number of dimensions does not match (is: ${dimensions.size})." }

            val (length, width, height) = dimensions.take(3)
            return create(length, width, height)
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
