package day3

import read

fun main() {
    val data = "day3".read()

    val treesHit = listOf(
        calculateTreesHit(data, 1 to 1),
        calculateTreesHit(data, 3 to 1),
        calculateTreesHit(data, 5 to 1),
        calculateTreesHit(data, 7 to 1),
        calculateTreesHit(data, 1 to 2)
    ).reduce { acc, i -> acc * i }
    // should be 195 for 3,1
    println("Trees hit: $treesHit")
}

fun calculateTreesHit(data: List<String>, steps: Pair<Int, Int>): Long {
    var currentIndex = 0
    var treeCount = 0L

    for (i in data.indices step steps.second) {
        val row = data[i]
        if (row[currentIndex] == '#') treeCount++

        currentIndex = (currentIndex + steps.first) % row.length
    }

    return treeCount
}