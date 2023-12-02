fun main() {
    val day = 3
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(day, 1)
    check(part1(testInput) == 1)

    val testInput2 = loadTestInput(day, 2)
    check(part2(testInput2) == 1)

    val input = loadInput(day)
    part1(input).println()
    part2(input).println()
}
