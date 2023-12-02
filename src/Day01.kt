fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { e -> sum += getCalibrationNumbers(e) }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { e -> sum += getCalibrationNumbersPart2(e) }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(1, 1)
    check(part1(testInput) == 142)

    val testInput2 = loadTestInput(1, 2)
    check(part2(testInput2) == 332)

    val input = loadInput(1)
    part1(input).println()
    part2(input).println()
}

private fun getCalibrationNumbers(input: String): Int {
    val first = input.first { c -> c.isDigit() }.toString()
    val last = input.last { c -> c.isDigit() }.toString()
    return (first + last).toInt()
}

private fun getCalibrationNumbersPart2(input: String): Int {
    var str = ""
    for (c in input) {
        str += c
        if (str.length >= 3) {
            str = str.replace("one", "1").replace("two", "2").replace("three", "3").replace("four", "4")
                .replace("five", "5").replace("six", "6").replace("seven", "7").replace("eight", "8")
                .replace("nine", "9")
        }
    }
    val first = str.first { c -> c.isDigit() }.toString()
    str = ""
    for (c in input.reversed()) {
        str += c
        if (str.length >= 3) {
            str = str.replace("eno", "1").replace("owt", "2").replace("eerht", "3").replace("ruof", "4")
                .replace("evif", "5").replace("xis", "6").replace("neves", "7").replace("thgie", "8")
                .replace("enin", "9")
        }
    }
    val last = str.first { c -> c.isDigit() }.toString()
    return (first + last).toInt()
}
