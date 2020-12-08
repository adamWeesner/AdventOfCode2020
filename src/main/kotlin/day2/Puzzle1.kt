package day2

import read

fun main() {
    val passwordList = "day2".read()
    val validCount = validatePasswords(passwordList)
    println("valid passwords: $validCount")
}

fun validatePasswords(passwords: List<String>): Int {
    var validCount = 0
    passwords.forEach {
        val split = it.split(" ")

        val rangeSplit = split[0].split("-")

        val range = IntRange(rangeSplit[0].toInt(), rangeSplit[1].toInt())
        val char = split[1].toCharArray().first()
        val password = split[2]

        if (password.toCharArray().filter { it == char }.size in range) validCount++
    }

    return validCount
}