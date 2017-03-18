
object Main {

  import Marker.Expand

  def main(args: Array[String]): Unit = {

    @ExtendScope
    val seq = Seq("one", "two".expandToValWithName("secondValue"))

    /**
      * @ExtendScope together with 'expandToValWithName' have created
      * a new expression in existing code:
      * val secondValue: String = "two"
       */
    assert(secondValue == "two")
  }
}
