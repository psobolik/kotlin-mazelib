package mazeLib

import java.util.*
import kotlin.random.Random

class Generator {
    fun generateMaze(rows: Int, cols: Int): CellGrid {
        val result = CellGrid(rows, cols)
        val activeCells = Stack<Cell>()

        addFirstCell(activeCells, result)
        while (activeCells.isNotEmpty()) {
            processActiveCell(activeCells, result)
        }
        return result
    }

    private fun addFirstCell(activeCells: Stack<Cell>, maze: CellGrid) {
        val coordinates =
            Coordinates(Random.nextInt(until = maze.rows), Random.nextInt(until = maze.cols))
        addNewCell(activeCells, maze, coordinates)
    }

    private fun processActiveCell(activeCells: Stack<Cell>, maze: CellGrid) {
        val currentCell = activeCells.peek()
        if (currentCell.isFullyAssigned()) {
            activeCells.pop()
        } else {
            // Examine a random unassigned neighbor cell
            val direction = getRandomUnassignedDirection(currentCell)
            val neighborCoordinates = currentCell.coordinates + direction.coordinates
            if (maze.inBounds(neighborCoordinates)) {
                var neighborCell = maze[neighborCoordinates]
                if (neighborCell == null) {
                    // Neighbor is unexamined, create a new cell with a passage in the selected direction
                    neighborCell = addNewCell(activeCells, maze, neighborCoordinates)
                    createPassage(currentCell, neighborCell, direction)
                } else {
                    // Neighbor has been examined, create a wall in selected direction
                    createWall(currentCell, neighborCell, direction)
                }
            } else {
                // Neighbor is out of bounds, create a border in selected direction
                createBorder(currentCell, direction)
            }
        }
    }

    private fun getRandomUnassignedDirection(cell: Cell): Direction {
        val unassignedDirections = cell.getUnassignedDirections()
        val index = Random.nextInt(until = unassignedDirections.size)
        return unassignedDirections[index]
    }

    private fun addNewCell(activeCells: Stack<Cell>, maze: CellGrid, coordinates: Coordinates): Cell {
        val newCell = Cell(coordinates)
        maze[coordinates] = newCell
        activeCells.push(newCell)
        return newCell
    }

    private fun createPassage(cell: Cell, otherCell: Cell, direction: Direction) {
        cell[direction] = CellPassage()
        otherCell[direction.opposite] = CellPassage()
    }

    private fun createWall(cell: Cell, otherCell: Cell, direction: Direction) {
        cell[direction] = CellWall()
        otherCell[direction.opposite] = CellWall()
    }

    private fun createBorder(cell: Cell, direction: Direction) {
        cell[direction] = CellBorder()
    }
}