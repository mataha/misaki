import org.gradle.api.JavaVersion

object Compatibility {
    val JAVA_VERSION = JavaVersion.VERSION_1_8
}

object Target {
    val BYTECODE_VERSION = Compatibility.JAVA_VERSION.toString()
}
