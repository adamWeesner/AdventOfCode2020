package day4

import read

// using this should return 6
private val testData = listOf(
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
    "iyr:2011 ecl:brn hgt:59in",
    "",
    "eyr:1972 cid:100",
    "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
    "",
    "iyr:2019",
    "hcl:#602927 eyr:1967 hgt:170cm",
    "ecl:grn pid:012533040 byr:1946",
    "",
    "hcl:dab227 iyr:2012",
    "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
    "",
    "hgt:59cm ecl:zzz",
    "eyr:2038 hcl:74454a iyr:2023",
    "pid:3556412378 byr:2007",
    "",
    "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980",
    "hcl:#623a2f",
    "",
    "eyr:2029 ecl:blu cid:129 byr:1989",
    "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
    "",
    "hcl:#888785",
    "hgt:164cm byr:2001 iyr:2015 cid:88",
    "pid:545766238 ecl:hzl",
    "eyr:2022",
    "",
    "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
)

fun main() {
    val data = "day4".read().map { it.trim() }
    val validCreds = calculateValidCredsAdvanced(data)

    println("Valid creds: $validCreds")
}

fun calculateValidCredsAdvanced(data: List<String>): Int {
    val creds = data.joinToString("\n").split("\n\n")
    var validCreds = 0

    creds.map { it.replace("\n", " ") }.forEach {
        val currentCreds = it.split(" ")
        val validCredsList = arrayListOf(false, false, false, false, false, false, false)

        if(currentCreds.size !in 7..8) return@forEach

        currentCreds.forEach creds@{ cred ->
            val (key, value) = cred.split(":")

            when (ValidCreds.valueOf(key)) {
                ValidCreds.byr -> validCredsList[0] = value.toInt() in 1920..2002
                ValidCreds.iyr -> validCredsList[1] = value.toInt() in 2010..2020
                ValidCreds.eyr -> validCredsList[2] = value.toInt() in 2020..2030
                ValidCreds.hgt -> {
                    when {
                        value.endsWith("cm") -> {
                            val size = value.replace("cm", "").toInt()
                            validCredsList[3] = size in 150..193
                        }
                        value.endsWith("in") -> {
                            val size = value.replace("in", "").toInt()
                            validCredsList[3] = size in 59..76
                        }
                    }
                }
                ValidCreds.hcl ->
                    validCredsList[4] = value.matches(Regex("^#([a-f0-9]){6}$", RegexOption.IGNORE_CASE))
                ValidCreds.ecl ->
                    validCredsList[5] = value.matches(Regex("^(amb|blu|brn|gry|grn|hzl|oth)$"))
                ValidCreds.pid ->
                    validCredsList[6] = value.matches(Regex("^[0-9]{9}$"))
                ValidCreds.cid -> validCredsList.add(true)
            }

            if (!validCredsList.contains(false)) validCreds++
        }
    }

    return validCreds
}

private enum class ValidCreds { byr, iyr, eyr, hgt, hcl, ecl, pid, cid }
