import java.io.File
import kotlin.math.pow

var cards = parseCards(readFileLineByLine("./input.txt"))
print(getCardSum(cards))

data class Card(var cardId: Int,
                var winningNumbers: Set<Int>,
                var actualNumber: Set<Int>)

fun getCardSum(cards: List<Card>) : Int {
    val repeater = cards.associateWith { 1 }.toMutableMap()

    cards.forEachIndexed { index, card ->
        for (i in 0..< repeater[card]!!) {
            var numWins = 0
            card.actualNumber.forEach {
                if (card.winningNumbers.contains(it)) {
                    val cardIndex = index + ++numWins
                    if(cardIndex < cards.size) repeater[cards[cardIndex]] = repeater[cards[cardIndex]]!! + 1
                }
            }
        }
    }

    return repeater.values.sum()
}

fun parseCards(lines: List<String>) : List<Card> {
    val cards = mutableListOf<Card>()

    for (line in lines) {
        val cardId = "\\d+".toRegex().find(line.split(":")[0])!!.value.toInt()
        val numbers = line.split(":")[1].trim().split(" | ")
        val winning = numbers[0].trim().split("\\s+".toRegex());
        val actual = numbers[1].trim().split("\\s+".toRegex());
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