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
        val result = ArrayList<Direction>()
        if (this[Direction.North] == null) result.add(Direction.North)
        if (this[Direction.East] == null) result.add(Direction.East)
        if (this[Direction.South] == null) result.add(Direction.South)
        if (this[Direction.West] == null) result.add(Direction.West)
        return result.toTypedArray()
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