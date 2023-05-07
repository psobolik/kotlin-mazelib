package mazeLib

class Parameters(private val args: Array<String>) {

    companion object {
        const val defaultRows = 10
        const val defaultCols = 10
        val rowArgs = arrayOf("-r", "--rows")
        val columnArgs = arrayOf("-c", "--cols", "--columns")
        var helpArgs = arrayOf("-h", "-?", "--help")
        const val helpText = """Generate and display a maze

Usage: mazeLib.RunKt [options]
Options:
    -h | -? | --help         Print this text and exit
    -c | --cols | --columns  Indicate the width of the maze. Default 10
    -r | --rows              Indicate the height of the maze. Default 10
"""
    }

    val showHelp = args.indexOfFirst { arg -> helpArgs.contains(arg) } >= 0
    val rows = getIntValue(rowArgs, defaultRows)
    val columns = getIntValue(columnArgs, defaultCols)

    private fun getIntValue(argNames: Array<String>, defaultValue: Int): Int {
        val index = args.indexOfLast { arg -> argNames.contains(arg) }
        if (index in args.indices && index + 1 in args.indices) {
            return args[index + 1].toIntOrNull() ?: defaultValue
        }
        return defaultValue
    }
}