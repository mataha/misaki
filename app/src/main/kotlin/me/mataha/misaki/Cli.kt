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
import me.mataha.misaki.runner.SolutionRunner
import me.mataha.misaki.util.extensions.printWriter
import java.io.InputStream

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

    private companion object {
        private const val EPILOG = """See also: README.md (general usage and examples)"""
    }

    override fun run() {
        val input = inputStream.bufferedReader().use { reader ->
            if (inputStream.default) {
                println("Enter your puzzle input:")
            }
            reader.readText()
        }

        try {
            val runner = koin.get<SolutionRunner>()
            val result = runner.run(origin, task, input)

            if (result != null) {
                outputStream.printWriter().use { writer ->
                    writer.println(result.result)

                    if (measure) {
                        writer.println("That took: ${result.elapsed}")
                    }
                }
            } else {
                System.err.println("$origin: '$task' was not found")
            }
        } catch (exception: Exception) {
            System.err.println("Error: ${exception.message}")
        }
    }
}

private const val STDIN_WRAPPER_CLASS_NAME = "com.github.ajalt.clikt.parameters.types.UnclosableInputStream"

private val InputStream.default: Boolean
    get() = this::class.qualifiedName == STDIN_WRAPPER_CLASS_NAME
