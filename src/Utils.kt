import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.time.measureTimedValue

fun Int.toDay() = "Day${this.toString().padStart(2, '0')}"

fun readTest(dayNb: Int) = readInput(dayNb, "_test")

/**
 * Reads lines from the given input txt file.
 */
fun readInput(dayNb: Int, suffix: String = "_input") = dayNb.toDay().let {
    File("src", "${it.lowercase()}/$it$suffix.txt")
}.readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun <A, B>checkTest(block: (A) -> B, input: A, expected: B) {
    val part = block.toString().split(" ")[1].replace("""\D""".toRegex(), "")
    val actual = block(input)
    if(actual != expected) {
        println("Test $part: $actual ❌ - expected: $expected")
        throw AssertionError("Test $part failed")
    } else {
        println("Test $part: $actual ✅")
    }
}

fun <A, B>runPart(block: (A) -> B, data: A): B {
    val part =  block.toString().split(" ")[1].replace("""\D""".toRegex(), "")
    return measureTimedValue {
        block(data)
    }.let {
        println("Part $part: ${it.value} \t in ${it.duration}")
        it.value
    }
}
