import kotlin.math.absoluteValue

data class Coordinates(val x: Int, val y: Int) {
    fun xDistanceTo(other: Coordinates) = x - other.x
    fun yDistanceTo(other: Coordinates) = y - other.y

    fun distanceTo(other: Coordinates) = Coordinates(x - other.x ,y - other.y)

    fun minXPoint(other: Coordinates) = if (x < other.x) this else other
    fun maxXPoint(other: Coordinates) = if (x > other.x) this else other
    fun minYPoint(other: Coordinates) = if (y < other.y) this else other
    fun maxYPoint(other: Coordinates) = if (y > other.y) this else other

    fun isGreaterX(other: Coordinates) = x > other.x
    fun isGreaterY(other: Coordinates) = y > other.y

    fun addX(value: Int) = this.copy( x= x + value)
    fun addY(value: Int) = this.copy(y = y + value)

    fun add(valX: Int, valY: Int) = this.copy( x= x + valX, y = y + valY)
    fun mult(value: Int) = this.copy( x= x * value, y = y * value)
    fun abs() = this.copy( x= x.absoluteValue, y = y.absoluteValue)
    fun areValidBounds(xRange: IntRange, yRange: IntRange) = x in xRange && y in yRange

    operator fun plus(other: Coordinates) = Coordinates(x + other.x, y + other.y)

    operator fun times(value: Int) = Coordinates(x * value, y * value)
}
