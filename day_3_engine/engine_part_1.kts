import java.io.File
import java.lang.Exception

val engine = Engine(readFileLineByLine("./input.txt"))
print(engine.getEngineSum())

class Engine(var lines: List<String>) {
    fun isSymbol(char: Char) : Boolean = !char.isDigit() && char != '.'
    fun getCharAt(x : Int, y : Int) : Char = try {lines[y][x]} catch (e: Exception) {'.'}
    fun hasSymbolNeighbor(x : Int, y : Int) : Boolean {
        if(isSymbol(getCharAt(x-1,y))) return true
        if(isSymbol(getCharAt(x,y-1))) return true
        if(isSymbol(getCharAt(x-1, y-1))) return true
        if(isSymbol(getCharAt(x, y+1))) return true
        if(isSymbol(getCharAt(x+1, y))) return true
        if(isSymbol(getCharAt(x+1, y+1))) return true
        if(isSymbol(getCharAt(x-1, y+1))) return true
        if(isSymbol(getCharAt(x+1, y-1))) return true
        return false
    }

    fun getEngineSum() : Int {
        var sum = 0
        for (y in 0..lines.size) {
            var curNum = 0
            var valid = false
            for (x in 0..lines.size) {
                if(getCharAt(x, y).isDigit()) {
                    curNum = curNum * 10 + getCharAt(x, y).digitToInt()
                    if (!valid) valid = hasSymbolNeighbor(x, y)
                } else {
                    if(valid) sum += curNum
                    curNum = 0;
                    valid = false
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