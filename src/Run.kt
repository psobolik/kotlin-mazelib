package mazeLib

fun main(args: Array<String>) {
    val wallChar = '▏'
    val floorChar = '_'
    val passageChar = ' '
    val noWallChar = ""

    val parameters = Parameters(args)

    if (parameters.showHelp) {
        println(Parameters.helpText)
        return
    }

    val generator = Generator()
    val maze = generator.generateMaze(parameters.rows, parameters.columns)

    val stringBuilder = StringBuilder()

    stringBuilder.appendLine("${maze.cols}x${maze.rows}")
    stringBuilder.appendLine(" ▁".repeat(maze.cols))
    var col = 0
    maze.forEach { cell ->
        stringBuilder.append(
            when (cell[Direction.West]) {
                is CellWall,
                is CellBorder -> wallChar

                else -> passageChar
            }
        )
        stringBuilder.append(
            when (cell[Direction.South]) {
                is CellWall,
                is CellBorder -> floorChar

                else -> passageChar
            }
        )
        stringBuilder.append(
            when (cell[Direction.East]) {
                is CellBorder -> wallChar
                else -> noWallChar
            }
        )
        if (++col >= maze.cols) {
            stringBuilder.appendLine()
            col = 0
        }
    }
    print(stringBuilder.toString())

    /*
        for (row in 0 until maze.rows) {
            for (col in 0 until maze.cols) {
                val coordinates = Coordinates(row, col)
                val cell = maze[coordinates]
                println("$coordinates: $cell")
            }
        }
    */
}