package day4

import read

// using this should return 2
val testData = listOf(
    "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
    "byr:1937 iyr:2017 cid:147 hgt:183cm",
    "",
    "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
    "hcl:#cfa07d byr:1929",
    "",
    "hcl:#ae17e1 iyr:2013",
    "eyr:2024",
    "ecl:brn pid:760753108 byr:1931",
    "hgt:179cm",
    "",
    "hcl:#cfa07d eyr:2025 pid:166559648",
    "iyr:2011 ecl:brn hgt:59in"
)

fun main() {
    val data = "day4".read()
    val validCreds = calculateValidCreds(data)

    println("Valid creds: $validCreds")
}

fun calculateValidCreds(data: List<String>): Int {
    val creds = data.joinToString("\n").split("\n\n")

    var validCreds = 0

    creds.map { it.replace("\n", " ") }.forEach {
        val currentCreds = it.split(" ")
        if (currentCreds.size == 8) {
            validCreds++
        } else if (currentCreds.size == 7) {
            var allMatched = true
            currentCreds.forEach {
                try {
                    ValidCred.valueOf(it.split(":")[0])
                } catch (e: IllegalArgumentException) {
                    allMatched = false
                }
            }
            if (allMatched) validCreds++
        }
    }

    return validCreds
}

enum class ValidCred { byr, iyr, eyr, hgt, hcl, ecl, pid }
