interface Day<PART1, PART2, DECODEDATA> {
    val nb: Int
    get() = this::class.java.name.split(".").first().removePrefix("day").toInt()

    fun part1(input: List<String>): PART1
    fun part2(input: List<String>): PART2
    fun decodeData(input: List<String>): DECODEDATA
}
