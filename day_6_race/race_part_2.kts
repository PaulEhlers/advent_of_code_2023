import java.io.File

data class TimeDistance(val time: Long, val recordDistance: Long)

val lines = readFile("./input.txt").split("\n")

val times = "\\d+".toRegex().findAll(lines[0].replace(" ", "")).map { it.value }.toList()
val record = "\\d+".toRegex().findAll(lines[1].replace(" ", "")).map { it.value }.toList()

val records = mutableListOf<TimeDistance>()
times.forEachIndexed { index, _ -> records.add(TimeDistance(time = times[index].toLong(),recordDistance =  record[index].toLong())) }

var total = 1;

records.forEach {
    var time = it.time
    var dis = it.recordDistance
    var records = 0
    for (i in 0 .. time) {
        if((time - i) * i > dis ) {
            records++
        }
    }
    total *= records
}

println(total)

fun readFile(fileName: String) : String {
    return File(fileName).readText()
}