package me.mataha.misaki

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.defaultStdin
import com.github.ajalt.clikt.parameters.types.defaultStdout
import com.github.ajalt.clikt.parameters.types.inputStream
import com.github.ajalt.clikt.parameters.types.outputStream
import me.mataha.misaki.domain.process
import me.mataha.misaki.service.SolutionService
import java.io.InputStream
import java.io.PrintWriter
import kotlin.time.measureTimedValue

class Cli(runScriptName: String) : CliktCommand(name = runScriptName, printHelpOnEmptyArgs = true, epilog = EPILOG) {
    init {
        versionOption(version) { it }
    }

    private val inputStream by option("-i", "--input", help = """File to read puzzle input from""")
        .inputStream().defaultStdin()

    private val outputStream by option("-o", "--output", help = """File to write results to""")
        .outputStream().defaultStdout()

    private val measure by option("--measure", help = """Enable/disable time measurement""")
        .flag("--no-measure", default = false)

    private val origin by argument(help = """Puzzle origin (site, company etc.)""")

    private val task by argument(help = """Task name""")

    override fun run() {
        val service = koin.get<SolutionService>()

        val data = service.get(origin, task)

        data?.let {
            if (inputStream.default) println("Enter your puzzle input:")

            val input = inputStream.bufferedReader().use { reader ->
                reader.readText().trim()
            }

            val solution = it.solution

            val result = measureTimedValue { solution.process(input) }

            PrintWriter(outputStream.bufferedWriter()).use { writer ->
                writer.println(result.value)
                if (measure) writer.println("That took: ${result.duration}")
            }
        } ?: System.err.println("Error: requested origin/task could not be found")
    }

    private companion object {
        private const val EPILOG = """See also: README.md (general usage and examples)"""
    }
}

private const val STDIN_WRAPPER_CLASS_NAME = "com.github.ajalt.clikt.parameters.types.UnclosableInputStream"

/* Ugly workaround - that or reflection coowabunga */
private val InputStream.default: Boolean
    get() = this::class.qualifiedName == STDIN_WRAPPER_CLASS_NAME
