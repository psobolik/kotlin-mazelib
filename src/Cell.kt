package mazeLib

class Cell(var coordinates: Coordinates) {
    private var northEdge: CellEdge? = null
    private var eastEdge: CellEdge? = null
    private var southEdge: CellEdge? = null
    private var westEdge: CellEdge? = null

    private fun unassignedEdgeCount(): Int {
        return getUnassignedDirections().size
    }

    fun isFullyAssigned(): Boolean {
        return unassignedEdgeCount() == 0
    }

    fun getUnassignedDirections(): Array<Direction> {
        return arrayOf(
            Direction.North,
            Direction.East,
            Direction.South,
            Direction.West
        ).filter { direction -> this[direction] == null }.toTypedArray()
    }

    operator fun get(direction: Direction): CellEdge? {
        return when (direction) {
            Direction.North -> northEdge
            Direction.East -> eastEdge
            Direction.South -> southEdge
            Direction.West -> westEdge
        }
    }

    operator fun set(direction: Direction, wall: CellEdge) {
        return when (direction) {
            Direction.North -> northEdge = wall
            Direction.East -> eastEdge = wall
            Direction.South -> southEdge = wall
            Direction.West -> westEdge = wall
        }
    }

    override fun toString(): String {
        return "Cell: $northEdge|$eastEdge|$southEdge|$westEdge"
    }
}