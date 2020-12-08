package day1

import read

fun main() {
    val expenses = "day1".read()
    val items = sumIs2020(expenses)
    println("summed to 2020: ${items.first} and ${items.second}")
    println("multiplication: ${items.first * items.second}")
}

private fun sumIs2020(expenses: List<String>): Pair<Int, Int> {
    expenses.forEachIndexed { index, expense ->
        expenses.subList(index, expenses.lastIndex).forEachIndexed { index2, expense2 ->
            val sum = expense.toInt() + expense2.toInt()
            if(sum == 2020) return expense.toInt() to expense2.toInt()
        }
    }
    return 0 to 0
}