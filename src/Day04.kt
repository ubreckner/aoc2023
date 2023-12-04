import kotlin.math.pow

fun main() {
    val day = 4

    fun part1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val winningNumberCnt = calcWinningNumberCount(line)
            res += if (winningNumberCnt > 0) 2.0.pow(winningNumberCnt - 1).toInt() else 0
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val cardbook = MutableList(input.size) { 0 }
        for ((lineNum, line) in input.withIndex()) {
            val winningNumberCnt = calcWinningNumberCount(line)
            cardbook[lineNum] += 1
            for (i in 1..winningNumberCnt) {
                cardbook[lineNum + i] += cardbook[lineNum]
            }
        }
        return cardbook.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(day, 1)
    val testOutput1 = part1(testInput)
    println("Test 1: $testOutput1")
    check(testOutput1 == 13)

    val testOutput2 = part2(testInput)
    println("Test 2: $testOutput2")
    check(testOutput2 == 30)

    val input = loadInput(day)
    part1(input).println()
    part2(input).println()
}


private fun calcWinningNumberCount(line: String): Int {
    val inputParts = line.substringAfter(": ").split(" | ")
    val winningNumbers = inputParts[0].split(" ").filter { it != "" }.map { it.toInt() }
    val myNumbers = inputParts[1].split(" ").filter { it != "" }.map { it.toInt() }
    var winningNumberCnt = 0
    for (num in myNumbers) {
        if (num in winningNumbers) {
            winningNumberCnt++
        }
    }
    return winningNumberCnt
}
