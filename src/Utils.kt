import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
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
