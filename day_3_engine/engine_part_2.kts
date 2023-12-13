import java.io.File
import java.lang.Exception
import kotlin.math.pow

val engine = Engine(readFileLineByLine("./input.txt"))
print(engine.getEngineSum())

class Engine(var lines: List<String>) {
    fun isGear(char: Char) : Boolean = char == '*'
    fun getCharAt(x : Int, y : Int) : Char = try {lines[y][x]} catch (e: Exception) {'.'}

    // TODO Error exists when two neighbors exist but they have the same number
    fun getTwoNumberNeighbors(x : Int, y : Int) : Pair<Int, Int>? {
        val set = mutableSetOf<Int>()

        if(getCharAt(x, y-1).isDigit()) set.add(getNumberAt(x, y-1))
        if(getCharAt(x-1, y).isDigit()) set.add(getNumberAt(x-1, y))
        if(getCharAt(x-1, y-1).isDigit()) set.add(getNumberAt(x-1, y-1))
        if(getCharAt(x+1, y).isDigit()) set.add(getNumberAt(x+1, y))
        if(getCharAt(x, y+1).isDigit()) set.add(getNumberAt(x, y+1))
        if(getCharAt(x+1, y+1).isDigit()) set.add(getNumberAt(x+1, y+1))
        if(getCharAt(x+1, y-1).isDigit()) set.add(getNumberAt(x+1, y-1))
        if(getCharAt(x-1, y+1).isDigit()) set.add(getNumberAt(x-1, y+1))

        if(set.size == 2) {
            return Pair(set.first(), set.last())
        }

        return null
    }

    fun getNumberAt(xO : Int, yO : Int) : Int {
        var num = 0
        var x = xO
        val y = yO
        var char = '.'
        var pot = 0;
        while(true) {
            char = getCharAt(x,y)
            if(!char.isDigit()) break
            num = num * 10 + char.digitToInt()
            x++
            pot++
        }

        x = xO-1
        while(true) {
            char = getCharAt(x,y)
            if(!char.isDigit()) break
            num += char.digitToInt() * (10.0.pow(pot.toDouble())).toInt()
            x--
            pot++
        }

        return num
    }

    fun getEngineSum() : Int {
        var sum = 0
        for (y in 0..lines.size) {
            for (x in 0..lines.size) {
                if(isGear(getCharAt(x, y))) {
                   val result = getTwoNumberNeighbors(x,y)
                    if(result != null) sum += result.first * result.second
                }
            }
        }
        return sum
    }
}

fun readFileLineByLine(fileName: String) : List<String> {
    val lines = mutableListOf<String>()
    File(fileName).forEachLine { lines.add(it) }
    return lines
}