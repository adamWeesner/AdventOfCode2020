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

        if (currentCreds.size !in 7..8) return@forEach

        val passport = Passport()

        currentCreds.forEach creds@{ cred ->
            val (key, value) = cred.split(":")

            when (key) {
                "byr" -> passport.byr = value
                "iyr" -> passport.iyr = value
                "eyr" -> passport.eyr = value
                "hgt" -> passport.hgt = value
                "hcl" -> passport.hcl = value
                "ecl" -> passport.ecl = value
                "pid" -> passport.pid = value
                "cid" -> passport.cid = value
            }
        }
        if (passport.isValid()) validCreds++
    }

    return validCreds
}

data class Passport(
    var byr: String = "",
    var iyr: String = "",
    var eyr: String = "",
    var hgt: String = "",
    var hcl: String = "",
    var ecl: String = "",
    var pid: String = "",
    var cid: String = ""
) {
    fun isValid(): Boolean {
        if (byr.toIntOrNull() !in 1920..2002) return false
        if (iyr.toIntOrNull() !in 2010..2020) return false
        if (eyr.toIntOrNull() !in 2020..2030) return false
        if (!hcl.matches(Regex("^#([a-f0-9]){6}$", RegexOption.IGNORE_CASE))) return false
        if (!ecl.matches(Regex("^(amb|blu|brn|gry|grn|hzl|oth)$"))) return false
        if (!pid.matches(Regex("^[0-9]{9}$"))) return false

        return (hgt.endsWith("cm") && hgt.replace("cm", "").toInt() in 150..193) ||
                (hgt.endsWith("in") && hgt.replace("in", "").toInt() in 59..76)
    }
}
