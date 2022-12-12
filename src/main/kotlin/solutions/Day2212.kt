package solutions

import java.lang.Exception

class Day2212(private val inputStrings: List<String>) {
    fun solveDayPartA(): Int {
        val adjacentList = inputStrings.toAdjacentList()
        val coordinatesOfStartNode = inputStrings.getCoordinatesOfNode('S').first()
        val coordinatesOfEndNode = inputStrings.getCoordinatesOfNode('E').first()

        return findShortestPath(
            adjacentList=adjacentList,
            from=coordinatesOfStartNode,
            to=coordinatesOfEndNode
        )
    }

    fun solveDayPartB(): Int {
        val adjacentList = inputStrings.toAdjacentList()
        val coordinatesOfStartNodes = inputStrings.getCoordinatesOfNode('S', 'a')
        val coordinatesOfEndNode = inputStrings.getCoordinatesOfNode('E').first()

        return coordinatesOfStartNodes.map {
            findShortestPath(
                adjacentList = adjacentList,
                from = it,
                to = coordinatesOfEndNode)
        }.filter { it > 0 }.minOrNull() ?: throw Exception("No minimum found")
    }

    // Dijkstra-Search
    private fun findShortestPath(
        adjacentList: MutableMap<String, List<String>>,
        from: String,
        to: String
    ): Int {
        val distances = adjacentList
            .keys
            .map { Pair(it, Int.MAX_VALUE) }
            .associateBy ({ it.first }, {it.second})
            .toMutableMap()
            .also { it[from] = 0 }

        val notVisitedNodes = distances.toMutableMap()

        while (notVisitedNodes.isNotEmpty()) {
            val nodeWithMinDistance = notVisitedNodes
                .entries
                .minByOrNull { it.value }
                ?.key
                .also { notVisitedNodes.remove(it) }

            for (node in adjacentList[nodeWithMinDistance]?.intersect(notVisitedNodes.keys) ?: listOf()) {
                val newDistance = distances[nodeWithMinDistance]?.plus(1) ?: throw Exception("Distance not found")

                if (newDistance < (distances[node] ?: throw Exception("Distance is null"))) {
                    notVisitedNodes[node] = newDistance
                    distances[node] = newDistance
                }
            }
        }

        return distances[to] ?: throw Exception("No Distance found")
    }


    private fun List<String>.toAdjacentList(): MutableMap<String, List<String>> {
        val adjacentList: MutableMap<String, List<String>> = mutableMapOf()

        for(x in inputStrings.indices) {
            for (y in inputStrings[0].indices) {
                val asciiCodeOfCurrentPosition = inputStrings[x][y].toAsciiCode()

                adjacentList["${x}_${y}"] = listOf(
                    Pair("${x-1}_${y}", inputStrings.getOrNull(x - 1)?.getOrNull(y)?.toAsciiCode()),
                    Pair("${x+1}_${y}", inputStrings.getOrNull(x + 1)?.getOrNull(y)?.toAsciiCode()),
                    Pair("${x}_${y-1}", inputStrings.getOrNull(x)?.getOrNull(y - 1)?.toAsciiCode()),
                    Pair("${x}_${y+1}", inputStrings.getOrNull(x)?.getOrNull(y + 1)?.toAsciiCode())
                )
                    .filter { it.second != null && (it.second == asciiCodeOfCurrentPosition || it.second!! <= asciiCodeOfCurrentPosition+1) }
                    .map { it.first }
            }
        }

        return adjacentList
    }

    private fun List<String>.getCoordinatesOfNode(vararg node: Char): List<String> {
        val coordinatesOfNode = mutableListOf<String>()

        for(x in inputStrings.indices) {
            for (y in inputStrings[0].indices) {
                if (node.contains(inputStrings[x][y])) {
                    coordinatesOfNode.add("${x}_${y}")
                }
            }
        }

        return coordinatesOfNode
    }

    private fun Char.toAsciiCode() = when(this) {
        'S' -> 'a'.code
        'E' -> 'z'.code
        else -> this.code
    }
}