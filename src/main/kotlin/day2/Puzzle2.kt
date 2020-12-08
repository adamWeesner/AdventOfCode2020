package day2

import read

fun main() {
    val passwordList = "day2".read()
    val validCount = validatePasswords2(passwordList)
    println("valid passwords: $validCount")
}

fun validatePasswords2(passwords: List<String>): Int {
    var validCount = 0
    passwords.forEach {
        val split = it.split(" ")

        val rangeSplit = split[0].split("-")

        val first = rangeSplit[0].toInt() - 1
        val second = rangeSplit[1].toInt() - 1
        val char = split[1].toCharArray().first()
        val password = split[2].toCharArray()

        if ((password[first] == char && password[second] != char) || (password[first] != char && password[second] == char)) validCount++
    }

    return validCount
}