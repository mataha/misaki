package me.mataha.misaki

import com.github.ajalt.clikt.completion.CompletionCandidates
import com.github.ajalt.clikt.core.Abort
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.groups.MutuallyExclusiveOptions
import com.github.ajalt.clikt.parameters.groups.default
import com.github.ajalt.clikt.parameters.groups.mutuallyExclusiveOptions
import com.github.ajalt.clikt.parameters.groups.single
import com.github.ajalt.clikt.parameters.options.NullableOption
import com.github.ajalt.clikt.parameters.options.RawOption
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.defaultStdout
import com.github.ajalt.clikt.parameters.types.inputStream
import com.github.ajalt.clikt.parameters.types.outputStream
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.runBlocking
import me.mataha.misaki.runner.SolutionRunner
import me.mataha.misaki.service.PuzzleService
import me.mataha.misaki.util.extensions.printWriter
import java.io.IOException
import java.io.InputStream

internal class Cli(runScriptName: String) :
    CliktCommand(name = runScriptName, printHelpOnEmptyArgs = true, epilog = EPILOG) {
    init {
        versionOption(version) { string -> string.trim() }
    }

    private val source by mutuallyExclusiveOptions(
        option(
            "-i", "--input",
            help = "File to read puzzle input from"
        ).inputStreamSource(),

        option(
            "-u", "--url",
            help = "URL to read puzzle input from"
        ).urlSource()
    ).single().defaultStdin()

    private val outputStream
            by option(
                "-o", "--output",
                help = "File to write results to"
            ).outputStream().defaultStdout()

    private val measure
            by option(
                "--measure",
                help = "Enable/disable time measurement"
            ).flag("--no-measure", default = false, defaultForHelp = "disable")

    private val token
            by option(
                "--token",
                help = "Session token to use with URL",
                metavar = "TOKEN",
                envvar = "MISAKI_APP_TOKEN"
            ).check("Session token must be a base16 string") { token ->
                token.matches(Regex("""[0-9a-f]+"""))
            }

    private val origin
            by argument(help = "Puzzle origin (site, company etc.)")

    private val task
            by argument(help = "Task name")

    private companion object {
        private const val EPILOG = "See also: README.md (general usage and examples)"
    }

    override fun run() {
        val service = koin.get<PuzzleService>()
        val runner = koin.get<SolutionRunner>()

        try {
            val puzzle = service.get(origin, task)

            val input = source.fetch(token)

            val result = runner.run(puzzle.solution, input)

            outputStream.printWriter().use { writer ->
                writer.println(result.value)
                if (measure) {
                    writer.println("That took: ${result.duration}")
                }
            }
        } catch (exception: Exception) {
            echo("Error: ${exception.toCompactString()}", err = true)
            throw Abort(error = true)
        }

        throw ProgramResult(0)
    }
}

private sealed class InputSource {
    abstract fun fetch(data: String?): String

    companion object
}

private fun RawOption.urlSource(): NullableOption<InputSource, InputSource> =
    convert({ "URL" }, CompletionCandidates.Hostname) { url ->
        UrlSource(url)
    }

private class UrlSource(private val url: String) : InputSource() {
    override fun fetch(data: String?): String = runBlocking {
        val factory = koin.get<HttpClientEngineFactory<*>>()

        HttpClient(factory).use { client ->
            client.get(url) {
                accept(ContentType.Text.Plain)
                doNotTrack()

                data?.let { token ->
                    setSessionToken(token)
                }
            }
        }
    }
}

private const val SESSION_COOKIE = "session"

private fun HttpRequestBuilder.doNotTrack() =
    this.header("DNT", 1)

private fun HttpRequestBuilder.setSessionToken(token: String) =
    this.header(HttpHeaders.Cookie, "$SESSION_COOKIE=$token")

private fun RawOption.inputStreamSource(): NullableOption<InputSource, InputSource> =
    inputStream().convert({ localization.fileMetavar() }, CompletionCandidates.Path) { inputStream ->
        InputStreamSource(inputStream)
    }

private class InputStreamSource(private val inputStream: InputStream) : InputSource() {
    override fun fetch(data: String?): String =
        inputStream.bufferedReader().use { reader ->
            if (inputStream.default) {
                println("Enter your puzzle input:")
            }
            reader.readText()
        }
}

private fun MutuallyExclusiveOptions<InputSource, InputSource?>.defaultStdin():
        MutuallyExclusiveOptions<InputSource, InputSource> = default(InputSource.default())

private fun InputSource.Companion.default(): InputSource = InputStreamSource(UnclosableInputStream(System.`in`))

private class UnclosableInputStream(private var delegate: InputStream?) : InputStream() {
    private val stream get() = delegate ?: throw IOException("Stream is closed")

    override fun available(): Int = stream.available()
    override fun read(): Int = stream.read()
    override fun read(b: ByteArray, off: Int, len: Int): Int = stream.read(b, off, len)
    override fun skip(n: Long): Long = stream.skip(n)
    override fun reset() = stream.reset()
    override fun markSupported(): Boolean = stream.markSupported()
    override fun mark(readlimit: Int) = stream.mark(readlimit)

    override fun close() {
        delegate = null
    }
}

/** Checks whether this stream is an unclosable [System.`in`] proxy. */
private val InputStream.default: Boolean
    get() = this::class == UnclosableInputStream::class

/** Returns a short description of this throwable in a compact form. */
private fun Throwable.toCompactString(): String {
    val name = this::class.simpleName
    val localized = this.localizedMessage

    return name + if (localized != null) ": $localized" else ""
}
