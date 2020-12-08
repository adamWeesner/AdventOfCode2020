import java.io.File

fun String.read() = File("./src/main/kotlin/$this").readLines()