package day5

import read
import kotlin.math.floor

fun main() {
    val data = "day5".read()
    val seadId = getSeatId(data)

    println("My seat: $seadId")
}

var offset: Int = 0

fun getSeatId(data: List<String>): Int {
    val mapped = data.map { it.substring(0, it.length - 3) to it.substring(it.length - 3) }
    val rows = mapped.map { it.first }
    val columns = mapped.map { it.second }

    val rowSet = rows.mapToNumber(0..127)
    val colSet = columns.mapToNumber(0..7)

    val seatIds = arrayListOf<Int>()

    rowSet.forEachIndexed { index, number ->
        seatIds.add(number * 8 + colSet[index])
    }

    val sortedIds = seatIds.sorted()
    offset = sortedIds.first()

    println("seats: ${sortedIds.size} ${sortedIds.lastIndex} | $offset")

    return findSeat(sortedIds)
}
// 838 to high
// 827 not right
// 244 not right
// 419 not right
// 524 not right
// 431 not right
// 587 not right
// 598 not right
// 600 not right
// 599

private fun List<String>.mapToNumber(range: IntRange): List<Int> = map { item ->
    var rowRange = range
    item.toCharArray().forEach {
        val currentRange = rowRange
        rowRange = calculateRange(it, currentRange)
    }
    rowRange.last
}

private fun findSeat(data: List<Int>): Int {
    var missedItem: Int = -1
    data.forEachIndexed { index, item ->
        if(item % 10 == 0) println(item.toString().padStart(3, '0'))
        else print("${item.toString().padStart(3, '0')}, ")
//        if (item - offset != index + 1) missedItem = index + 1 + offset
        when {
            index == 0 -> {}
            index == data.lastIndex -> {}
            data[index - 1] + 1 != item || data[index + 1] - 1 != item -> {
                //println("didnt match ${data[index]} - $item | ${data[index - 1]} | ${data[index + 1]}")
                missedItem = item
            }
        }
    }

    return missedItem
}

private fun calculateRange(command: Char, range: IntRange): IntRange {
    return when (command) {
        'F', 'L' -> range.first..floor((range.first + range.last) / 2f).toInt()
        'B', 'R' -> floor((range.first + range.last) / 2f).toInt()..range.last
        else -> throw IllegalArgumentException("Command is not valid: $command")
    }
}
