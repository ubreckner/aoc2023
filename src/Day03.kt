fun main() {
    val day = 3

    fun part1(input: List<String>): Int {
        val numberList = mutableListOf<SchematicNumber>()
        val symbolList = mutableListOf<Pair<Int, Int>>()
        for ((y, line) in input.withIndex()) {
            var curNumberStr = ""
            for ((x, char) in line.withIndex()) {
                if (char.isDigit()) {
                    curNumberStr += char
                    if (x == line.length - 1) {
                        numberList.add(SchematicNumber(Pair(x - 1, y), curNumberStr))
                    }
                } else {
                    if (char != '.') {
                        symbolList.add(Pair(x, y))
                    }
                    if (curNumberStr != "") {
                        numberList.add(SchematicNumber(Pair(x - 1, y), curNumberStr))
                        curNumberStr = ""
                    }
                }
            }
        }

        return testAdjacency(numberList, symbolList)
    }

    fun part2(input: List<String>): Int {
        val numberList = mutableListOf<SchematicNumber>()
        val symbolList = mutableListOf<Pair<Int, Int>>()
        for ((y, line) in input.withIndex()) {
            var curNumberStr = ""
            for ((x, char) in line.withIndex()) {
                if (char.isDigit()) {
                    curNumberStr += char
                    if (x == line.length - 1) {
                        numberList.add(SchematicNumber(Pair(x - 1, y), curNumberStr))
                    }
                } else {
                    if (char != '.') {
                        symbolList.add(Pair(x, y))
                    }
                    if (curNumberStr != "") {
                        numberList.add(SchematicNumber(Pair(x - 1, y), curNumberStr))
                        curNumberStr = ""
                    }
                }
            }
        }

        return calculateGearRatios(numberList, symbolList)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(day, 1)
    check(part1(testInput) == 4361)

    val testInput2 = loadTestInput(day, 1)
    check(part2(testInput2) == 467835)

    val input = loadInput(day)
    part1(input).println()
    part2(input).println()
}

private fun testAdjacency(numberList: MutableList<SchematicNumber>, symbolList: MutableList<Pair<Int, Int>>): Int {
    var res = 0
    nextrow@ for (num in numberList) {
        for (symbol in symbolList) {
            if (
            //symbol above number
                (symbol.second == num.end.second - 1 && symbol.first >= num.end.first - num.valueString.length && symbol.first <= num.end.first + 1) ||
                //symbol besides number
                (symbol.second == num.end.second && (symbol.first == num.end.first - num.valueString.length || symbol.first == num.end.first + 1)) ||
                //symbol below number
                (symbol.second == num.end.second + 1 && symbol.first >= num.end.first - num.valueString.length && symbol.first <= num.end.first + 1)
            ) {
                res += num.value
                continue@nextrow
            }
            //Symbols more than a line apart the current number can be skipped
            if (symbol.second > num.end.second + 1) {
                continue@nextrow
            }
        }
    }
    return res
}

private fun calculateGearRatios(
    numberList: MutableList<SchematicNumber>,
    symbolList: MutableList<Pair<Int, Int>>
): Int {
    var res = 0
    nextrow@ for (symbol in symbolList) {
        val associatedNumberList = mutableListOf<SchematicNumber>()
        for ((cnt, num) in numberList.withIndex()) {
            if (
            //symbol above number
                symbol.second == num.end.second - 1 && symbol.first >= num.end.first - num.valueString.length && symbol.first <= num.end.first + 1 ||
                //symbol besides number
                (symbol.second == num.end.second && (symbol.first == num.end.first - num.valueString.length || symbol.first == num.end.first + 1)) ||
                //symbol below number
                (symbol.second == num.end.second + 1 && symbol.first >= num.end.first - num.valueString.length && symbol.first <= num.end.first + 1)
            ) {
                associatedNumberList.add(num)
                if (associatedNumberList.size > 2) {
                    continue@nextrow
                }
            }
            //Symbols more than a line apart the current number can be skipped
            if (num.end.second > symbol.second + 1 || cnt == numberList.size - 1) {
                if (associatedNumberList.size == 2) {
                    res += associatedNumberList[0].value * associatedNumberList[1].value
                }
                continue@nextrow
            }
        }
    }
    return res
}

class SchematicNumber(val end: Pair<Int, Int>, val valueString: String) {
    val value = valueString.toInt()

}
