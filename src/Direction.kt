package mazeLib

enum class Direction {
    North,
    East,
    South,
    West;

    val right: Direction
        get() = when (this) {
            North -> East
            East -> South
            South -> West
            West -> North
        }

    val left: Direction
        get() = when (this) {
            North -> West
            East -> North
            South -> East
            West -> South
        }

    val opposite: Direction
        get() = when (this) {
            North -> South
            East -> West
            South -> North
            West -> East
        }

    val coordinates: Coordinates
        get() = when (this) {
            North -> Coordinates.up
            East -> Coordinates.right
            South -> Coordinates.down
            West -> Coordinates.left
        }
}