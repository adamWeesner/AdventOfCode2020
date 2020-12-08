package day1

import read

fun main() {
    val expenses = "day1".read()
    val items = sumIs2020(expenses)
    println("summed to 2020: ${items.first}, ${items.second}, ${items.third}")
    println("multiplication: ${items.first * items.second * items.third}")
}

private fun sumIs2020(expenses: List<String>): Triple<Int, Int, Int> {
    expenses.forEachIndexed { index, expense ->
        expenses.subList(index + 1, expenses.lastIndex).forEachIndexed { index2, expense2 ->
            expenses.subList(index2 + 1, expenses.lastIndex).forEach { expense3 ->
                val sum = expense.toInt() + expense2.toInt() + expense3.toInt()
                if (sum == 2020) return Triple(expense.toInt(), expense2.toInt(), expense3.toInt())
            }
        }
    }
    return Triple(0, 0, 0)
}