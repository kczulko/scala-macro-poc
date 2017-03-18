
object Marker {
  implicit class Expand[T](t: T) {
    def expandToValWithName(name: String): T = t
  }
}
