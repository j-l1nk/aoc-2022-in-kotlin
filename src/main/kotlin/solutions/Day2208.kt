package solutions

class Day2208(private val inputStrings: List<String>) {
    fun solveDayPartA() = inputStrings
        .map { line -> line.map { Tree(it.toString().toInt()) } }
        .map { it.setVisible() }
        .map { it.reversed().setVisible() }
        .transposeMatrix()
        .map { it.setVisible() }
        .flatMap { it.reversed().setVisible() }
        .count { it.visible }

    fun solveDayPartB(): Int {
        val trees = inputStrings.map { it.map { height -> Tree(height = height.toString().toInt())} }
        val transposedTrees = trees.transposeMatrix()

        return trees
            .flatMapIndexed { x, line ->  line.mapIndexed { y, tree ->
                val left = trees[x].subList(0, y+1).reversed().stepsToNextTree()
                val top = transposedTrees[y].subList(0, x+1).reversed().stepsToNextTree()
                val right = trees[x].subList(y, trees[x].size).stepsToNextTree()
                val down = transposedTrees[y].subList(x, trees[x].size).stepsToNextTree()

                left * right * top * down
            } }
            .maxOrNull() ?: 0
    }
}

fun List<List<Tree>>.transposeMatrix() = this
    .flatMap { it.mapIndexed { index, tree -> Pair(index, tree) } }
    .groupBy { it.first }
    .values
    .map { it.map { treePair -> treePair.second } }

fun List<Tree>.setVisible() = this
    .runningReduceIndexed { index, treeA, treeB ->
        if (index == 1) {
            treeA.heighestValueInLine = treeA.height
            treeA.visible = true
        }

        when(treeB.height > treeA.heighestValueInLine){
            true -> Tree(height = treeB.height, visible = true, heighestValueInLine = treeB.height)
            else -> Tree(height = treeB.height, visible = treeB.visible, heighestValueInLine = treeA.heighestValueInLine)
        } }

fun List<Tree>.stepsToNextTree(): Int {
    val first = this.firstOrNull() ?: return 0
    val result = this.drop(1).indexOfFirst { first.height <= it.height }
    return when (result == -1) {
        true -> this.size - 1
        else -> result + 1
    }
}

data class Tree(
    val height: Int,
    var visible: Boolean = false,
    var heighestValueInLine: Int = -1
)
