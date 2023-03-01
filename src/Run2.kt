package mazeLib

fun main(args: Array<String>) {
    val wallChar = '|'
    val floorChar = '_'
    val passageChar = ' '

    val parameters = Parameters(args)

    if (parameters.showHelp) {
        println(Parameters.helpText)
        return
    }

    val maze = MazeGenerator.generateMaze(parameters.rows, parameters.columns)
    println("${maze.rows}x${maze.cols}")
    val line = mutableListOf<Char>()
    val lines = mutableListOf<String>()
    for (cell in maze) {
        line.add(when (cell[Direction.West]) {
            is CellWall,
            is CellBorder -> wallChar
            else -> passageChar
        })
        line.add(when (cell[Direction.South]) {
            is CellWall,
            is CellBorder -> floorChar
            else -> passageChar
        })
        if (cell[Direction.East] is CellBorder) {
            line.add(wallChar)
            lines.add(line.joinToString(""))
            line.clear()
        }
    }
//    lines.add((0..maze.cols).joinToString("") { "%02d".format(it) })
    lines.add((0..maze.cols).joinToString("") { "_ " })
    lines.reversed().forEach { println(it) }
}