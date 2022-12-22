package mazeLib

fun main(args: Array<String>) {
    val parameters = Parameters(args)

    if (parameters.showHelp) {
        println(Parameters.helpText)
        return
    }
    
    val generator = Generator()
    val maze = generator.generateMaze(parameters.rows, parameters.columns)

    println("${maze.cols}x${maze.rows}")

    for (col in 0 until maze.cols) print(" _")
    println()
    for (row in 0 until maze.rows) {
        var s = ""
        for (col in 0 until maze.cols) {
            val coordinates = Coordinates(maze.rows - 1 - row, col)
            val cell = maze[coordinates]
            s += when (cell?.get(Direction.West)) {
                is CellWall,
                is CellBorder -> "|"
                else -> " "
            }
            s += when (cell?.get(Direction.South)) {
                is CellWall,
                is CellBorder -> "_"
                else -> " "
            }
            s += when (cell?.get(Direction.East)) {
                is CellBorder -> "|"
                else -> ""
            }
        }
        println(s)
    }
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