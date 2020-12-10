package day5

import read
import kotlin.math.floor

private val sampleData = listOf(
    "BFFFBBFRRR",
    "FFFBBBFRRR",
    "BBFFBBFRLL"
)

fun main() {
    val data = "day5".read()
    val seadId = getHighestSeatId(data)

    println("Highest seat id: $seadId")
}

fun getHighestSeatId(data: List<String>): Int {
    val mapped = data.map { it.substring(0, it.length - 3) to it.substring(it.length - 3) }
    val rows = mapped.map { it.first }
    val columns = mapped.map { it.second }

    val rowSet = rows.mapToNumber(0..127)
    val colSet = columns.mapToNumber(0..7)

    var highestSeatId = 0

    rowSet.forEachIndexed { index, number ->
        val seatId = number * 8 + colSet[index]
        if (seatId > highestSeatId) highestSeatId = seatId
    }

    return highestSeatId
}

private fun List<String>.mapToNumber(range: IntRange): List<Int> = map { item ->
    var rowRange = range
    item.toCharArray().forEach {
        val currentRange = rowRange
        rowRange = calculateRange(it, currentRange)
    }
    rowRange.last
}

private fun calculateRange(command: Char, range: IntRange): IntRange {
    return when (command) {
        'F', 'L' -> range.first..floor((range.first + range.last) / 2f).toInt()
        'B', 'R' -> floor((range.first + range.last) / 2f).toInt()..range.last
        else -> throw IllegalArgumentException("Command is not valid: $command")
    }
}
