package solutions

class Day2207(private val inputStrings: List<String>) {
    fun solveDayPartA(): Long {
        val files = inputStrings
            .joinToString(",")
            .split("\$ cd ")
            .filter { it.isNotEmpty() && it != "..," }
            .map { it.toFile() }
            .associateBy { it.name }

        return files
            .map { it.value.getSize() + getAllDirectories(files, it.value.getDirectories()).sumOf { dirName -> files[dirName]?.getSize() ?: 0L } }
            .filter { it <= 100000L }
            .sum()
    }

    private fun getAllDirectories(files: Map<String, File>, directories: List<String>): List<String> = directories
        .map { files[it] }
        .map {
            when (it?.getDirectories()?.size == 0) {
                true -> it!!.name
                false -> it!!.name + getAllDirectories(files, it.getDirectories())
            }
        }

    fun solveDayPartB() = inputStrings

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

    private fun Map.Entry<String, File>.getSizeOfDirectories(files: Map<String, File>) = this
        .value
        .getSize()
}
