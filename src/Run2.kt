package mazeLib

fun main(args: Array<String>) {
    val wallChar = '▏'
    val floorChar = '_'
    val passageChar = ' '

    val parameters = Parameters(args)

    if (parameters.showHelp) {
        println(Parameters.helpText)
        return
    }

    val maze = MazeGenerator.generateMaze(parameters.rows, parameters.columns)
    println("${maze.rows}x${maze.cols}")
    repeat(maze.cols) { print("_ ") }
    println("_")
    for (cell in maze) {
        print(when (cell[Direction.West]) {
            is CellWall,
            is CellBorder -> wallChar
            else -> passageChar
        })
        print(when (cell[Direction.South]) {
            is CellWall,
            is CellBorder -> floorChar
            else -> passageChar
        })
        if (cell[Direction.East] is CellBorder) {
            println(wallChar)
        }
    }
}
