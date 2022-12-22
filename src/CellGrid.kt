package mazeLib

class CellGrid(val rows: Int, val cols: Int) : Iterator<Cell> {
    private var next = Coordinates(0, 0)

    val grid = Array<Array<Cell?>>(rows) { _ -> Array<Cell?>(cols) { _ -> null } }

    override fun hasNext(): Boolean {
        return inBounds(Coordinates(next.row, next.col + 1))
                || inBounds(Coordinates(next.row + 1, 0))
    }

    override fun next(): Cell {
        var result: Cell? = null
        var test = Coordinates(next.row, next.col + 1)
        if (inBounds(test)) {
            next = test
            result = get(next)
        } else {
            test = Coordinates(next.row + 1, 0)
            if (inBounds(test)) {
                next = test
                result = get(next)
            }
        }
        return result!!
    }

    operator fun get(coordinates: Coordinates): Cell? {
        return grid[coordinates.row][coordinates.col]
    }

    operator fun set(coordinates: Coordinates, value: Cell) {
        grid[coordinates.row][coordinates.col] = value
    }

    fun inBounds(coordinates: Coordinates): Boolean {
        return rowInBounds(coordinates.row) && colInBounds(coordinates.col)
    }

    private fun rowInBounds(row: Int): Boolean {
        return row in 0 until rows
    }

    private fun colInBounds(col: Int): Boolean {
        return col in 0 until cols
    }
}