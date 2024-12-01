import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.time.measureTimedValue


fun readTest(day: String, suffix: String = "_test") = readInput(day, suffix)

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: String, suffix: String = "_input") = File("src", "${day.lowercase()}/$day$suffix.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun <A, B>checkTest(part: Int, block: (A) -> B, input: A, expected: B) {
    val actual = block(input)
    check(actual == expected) { "Test $part failed - actual: `$actual` != expected `$expected`" }
}

fun <A, B>runPart(part: Int, data: A, block: (A) -> B): B {
    return measureTimedValue {
        block(data)
    }.let {
        println("Part $part: ${it.value} \t in ${it.duration}")
        it.value
    }
}
