import java.io.File
import kotlin.math.pow

var cards = parseCards(readFileLineByLine("./input.txt"))
print(getCardSum(cards))

data class Card(var cardId: Int,
                var winningNumbers: Set<Int>,
                var actualNumber: Set<Int>)

fun getCardSum(cards: List<Card>) : Int {
    var sum = 0
    cards.forEach { card: Card ->
        var pot = -1
        card.actualNumber.forEach {
            if(card.winningNumbers.contains(it)) pot++
        }
        if(pot >= 0) sum += 2.0.pow(pot).toInt()
    }
    return sum
}

fun parseCards(lines: List<String>) : List<Card> {
    val cards = mutableListOf<Card>()

    for (line in lines) {
        var cardId = "\\d+".toRegex().find(line.split(":")[0])!!.value.toInt()
        var numbers = line.split(":")[1].trim().split(" | ")
        var winning = numbers[0].trim().split("\\s+".toRegex());
        var actual = numbers[1].trim().split("\\s+".toRegex());
        cards.add(Card(
                cardId = cardId,
                winningNumbers = winning.map { it.toInt() }.toSet(),
                actualNumber = actual.map { it.toInt() }.toSet(),
        ))
    }

    return cards
}

fun readFileLineByLine(fileName: String) : List<String> {
    val lines = mutableListOf<String>()
    File(fileName).forEachLine { lines.add(it) }
    return lines
}