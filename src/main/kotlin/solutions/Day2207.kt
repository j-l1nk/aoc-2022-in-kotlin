package solutions

import java.lang.Exception

class Day2207(private val inputStrings: List<String>) {
    fun solveDayPartA(): Long {
        val directories = inputStrings
            .joinToString(",")
            .split("\$ cd ")
            .filter { it.isNotEmpty() && it != "..," }
            .map { it.toFile() }
            .associateBy { it.name }

        return directories
            .map { dir -> dir.value.getSize() + getAllSubDirectories(rootDir = dir.value, allDirs = directories).sumOf { directories[it]?.getSize() ?: throw Exception("Size not found") } }
            .filter { it <= 100000L }
            .sum()
    }

    fun solveDayPartB() = inputStrings

    fun getAllSubDirectories(rootDir: File, allDirs: Map<String, File>): List<String> {
        return allDirs[rootDir.name]!!
            .getDirectories()
            .flatMap { listOf(it) + getAllSubDirectories(rootDir=allDirs[it]!!, allDirs=allDirs) }
    }

    data class File(
        val name: String,
        val fileType: FileType,
        val files: List<String>
    ) {
        fun getSize() = files
            .map { it.split(" ").first() }
            .filter { it.toLongOrNull() != null }
            .sumOf { it.toLong() }

        fun getDirectories(): List<String> = files
            .filter { it.startsWith("dir") }
            .map { it.replace("dir ", "") }
    }

    private fun String.toFile() = this
        .split(",")
        .filterNot { it == "$ ls" || it.isBlank() }
        .let { File(it.first(), FileType.DIRECTORY, it.takeLast(it.size-1)) }

    enum class FileType {
        FILE,
        DIRECTORY
    }
}
