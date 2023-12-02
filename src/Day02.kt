fun main() {
    val day = 2

    fun part1(input: List<String>): Int {
        val bag = mapOf("red" to 12, "green" to 13, "blue" to 14)
        var result = 0
        nextline@ for (line in input) {
            val inputSplit = line.split(": ")
            var errorInGame = false
            val gameId = inputSplit[0].substringAfter("Game ").toInt()
            val draws = inputSplit[1].split("; ")
            for (draw in draws) {
                val cubes = draw.split(", ")
                val valuesMap = cubes.map { it.split(" ") }.associate { it[1] to it[0].toInt() }
                valuesMap.keys.forEach { key ->
                    if (valuesMap[key]!! > bag[key]!!) {
                        errorInGame = true
                    }
                }
                if (errorInGame) continue@nextline
            }
            result += gameId
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val minimalCubes = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            val draws = line.split(": ")[1].split("; ")
            for (draw in draws) {
                val cubes = draw.split(", ")
                val valuesMap = cubes.map { it.split(" ") }.associate { it[1] to it[0].toInt() }
                valuesMap.keys.forEach { key ->
                    if (minimalCubes[key] == 0 || valuesMap[key]!! > minimalCubes[key]!!) {
                        minimalCubes[key] = valuesMap[key]!!
                    }
                }
            }

            result += minimalCubes.values.reduce { acc: Int, i: Int -> acc * i }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadTestInput(day, 1)
    check(part1(testInput) == 8)

    val testInput2 = loadTestInput(day, 2)
    check(part2(testInput2) == 2286)

    val input = loadInput(day)
    part1(input).println()
    part2(input).println()
}
