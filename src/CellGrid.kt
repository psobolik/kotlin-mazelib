package mazeLib

class CellGrid(val rows: Int, val cols: Int) : Iterator<Cell> {
    // Iterate left to right, bottom to top
    private var next = Coordinates(rows, -1)

    private val grid = Array(rows) { _ -> Array<Cell?>(cols) { _ -> null } }

    override fun hasNext(): Boolean {
        return isCoordinateInBounds(Coordinates(next.row, next.col + 1))
                || isCoordinateInBounds(Coordinates(next.row - 1, 0))
    }

    override fun next(): Cell {
        var test = Coordinates(next.row, next.col + 1)
        if (isCoordinateInBounds(test)) {
            next = test
            return get(next)!!
        }
        test = Coordinates(next.row - 1, 0)
        if (isCoordinateInBounds(test)) {
            next = test
            return get(next)!!
        }
        throw Exception("Error in CellGrid iterator!")
    }

    operator fun get(coordinates: Coordinates): Cell? {
        return grid[coordinates.row][coordinates.col]
    }

    operator fun set(coordinates: Coordinates, value: Cell) {
        grid[coordinates.row][coordinates.col] = value
    }

    fun isCoordinateInBounds(coordinates: Coordinates): Boolean {
        return coordinates.row in 0 until rows &&
                coordinates.col in 0 until cols
    }
}