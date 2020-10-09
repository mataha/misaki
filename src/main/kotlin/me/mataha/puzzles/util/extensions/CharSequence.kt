package me.mataha.puzzles.util.extensions

fun CharSequence.zipWithAfterNext(): List<Pair<Char, Char>> = zipWithAfterNext { a, b -> a to b }

inline fun <Result> CharSequence.zipWithAfterNext(transform: (a: Char, b: Char) -> Result): List<Result>
{
    val size = length - 2
    if (size < 2)
    {
        return emptyList()
    }

    val result = ArrayList<Result>(size)
    for (index in 0 until size)
    {
        result.add(transform(this[index], this[index + 2]))
    }
    return result
}
