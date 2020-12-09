package day3

import read

fun main() {
    val data = "day3".read()

    val treesHit = calculateTreesHit(data)
    println("Trees hit: $treesHit")
}

fun calculateTreesHit(data: List<String>): Int {
    var currentIndex = 0
    var treeCount = 0

    for (row in data) {
        if(row[currentIndex] == '#') treeCount++

        currentIndex = (currentIndex + 3) % row.length
    }

    return treeCount
}