fun main() {
    val day = 6
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(day, 1)
    val testOutput1 = part1(testInput)
    println("Test 1: $testOutput1")
    check(testOutput1 == testInput.size)

    val testOutput2 = part1(testInput)
    println("Test 2: $testOutput2")
    check(testOutput2 == testInput.size)

    val input = loadInput(day)
    part1(input).println()
    part2(input).println()
}
