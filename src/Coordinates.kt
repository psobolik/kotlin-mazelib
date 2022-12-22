package mazeLib

import kotlin.math.pow

class Coordinates(var row: Int, var col: Int) {
    companion object {
        var up = Coordinates(1, 0)
        var right = Coordinates(0, 1)
        var down = Coordinates(-1, 0)
        var left = Coordinates(0, -1)
    }

    operator fun plus(increment: Coordinates): Coordinates {
        return Coordinates(row + increment.row, col + increment.col)
    }

    override fun equals(other: Any?): Boolean {
        return other is Coordinates
                && row == other.row
                && col == other.col
    }

    override fun hashCode(): Int {
        return row.toFloat().pow(col.toFloat()).toInt()
    }

    override fun toString(): String {
        return "($row, $col)"
    }
}