package mazeLib

import java.lang.Exception

class CellGrid(val rows: Int, val cols: Int) : Iterator<Cell> {
    // Iterate array left to right, bottom to top
    private var next = Coordinates(rows, -1)

    private val grid = Array(rows) { _ -> Array<Cell?>(cols) { _ -> null } }

    override fun hasNext(): Boolean {
        return areCoordinatesInBounds(Coordinates(0, next.col + 1))
                || areCoordinatesInBounds(Coordinates(next.row - 1, 0))
    }

    override fun next(): Cell {
        var test = Coordinates(next.row, next.col + 1)
        if (areCoordinatesInBounds(test)) {
            next = test
            return get(next)!!
        }
        test = Coordinates(next.row - 1, 0)
        if (areCoordinatesInBounds(test)) {
            next = test
            return get(next)!!
        }
        throw Exception("Invalid call to CellGrid.next()")
    }

    operator fun get(coordinates: Coordinates): Cell? {
        return grid[coordinates.row][coordinates.col]
    }

    operator fun set(coordinates: Coordinates, value: Cell) {
        grid[coordinates.row][coordinates.col] = value
    }

    fun areCoordinatesInBounds(coordinates: Coordinates): Boolean {
        return coordinates.row in 0 until rows && coordinates.col in 0 until cols
    }
}