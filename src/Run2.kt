package mazeLib

fun main(args: Array<String>) {
    val parameters = Parameters(args)

    if (parameters.showHelp) {
        println(Parameters.helpText)
        return
    }

    val maze = MazeGenerator.generateMaze(parameters.rows, parameters.columns)
    println("${maze.rows}x${maze.cols}")
    var index = 0
    var s = ""
    val lines = mutableListOf<String>()
    for (cell in maze) {
        s += when (cell[Direction.West]) {
            is CellWall,
                is CellBorder -> "|"
            else -> " "
        }
        s += when (cell[Direction.South]) {
            is CellWall,
                is CellBorder -> "_"
            else -> " "
        }
        s += when (cell[Direction.East]) {
            is CellBorder -> "|"
            else -> ""
        }
        if (++index % maze.cols == 0) {
            lines.add(0, s)
            s = ""
        }
    }
//    lines.add((0..maze.cols).joinToString("") { "%02d".format(it) })
    lines.add(0, (0..maze.cols).joinToString("") { "_ " })
    for (line in lines) {
        println(line)
    }
}