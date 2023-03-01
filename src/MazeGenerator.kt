package mazeLib

object MazeGenerator {
    fun generateMaze(rows: Int, cols: Int): CellGrid {
        val generator = Generator()
        return generator.generateMaze(rows, cols)
    }
}