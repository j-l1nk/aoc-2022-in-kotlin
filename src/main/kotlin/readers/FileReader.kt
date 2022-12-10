package readers

import java.io.File

object FileReader {
    fun readIntsFromFile(filePath: String) = this.readStringsFromFile(filePath).map { it.toInt() }

    fun readIntLineFromFile(filePath: String) = this.readStringsFromFile(filePath).flatMap { it.split(",") }.map { it.toInt() }

    fun readLongLineFromFile(filePath: String) = this.readStringsFromFile(filePath).flatMap { it.split(",") }.map { it.toLong() }

    fun readLongsFromFile(filePath: String): List<Long> {
        return this.readStringsFromFile(filePath).map { it.toLong() }
    }

    fun readStringsFromFile(filePath: String): List<String> {
        return this.getFileAsText(filePath).split(System.getProperty("line.separator")).map { it.replace("\r", "") }
    }

    private fun getFileAsText(filePath: String) = File(filePath).readText()

    /**
     * @return Pair(List<Drawn, Boards>)
     */
    fun readBingoInputs(filePath: String): Pair<List<Int>, List<Pair<Int, Boolean>>> {
        val input = this.readStringsFromFile(filePath=filePath)
        val boards = input.toMutableList().drop(1).joinToString(" ").split(" ").filterNot {it == ""}.map { it.toInt() }
        return Pair(input[0].split(",").map { it.toInt() }, boards.map { Pair(it, false) })
    }

    fun readhydroThermalVents(filePath: String) = readStringsFromFile(filePath=filePath).map { it.split(",", " -> ") }.map { Pair(Pair(it[0].toInt(), it[1].toInt()), Pair(it[2].toInt(), it[3].toInt())) }

    fun readDisplayNotes(filePath: String) = this.readStringsFromFile(filePath = filePath).map { Pair(it.substringBefore(" |"), it.substringAfter("| ")) }.map { Pair(it.first.split(" "), it.second.split(" ")) }
}