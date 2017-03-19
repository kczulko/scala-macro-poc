package com.github.kczulko

object Main {

  import com.github.kczulko.Marker.Expand

  def main(args: Array[String]): Unit = {

    @ExtendScope
    val seq = Seq("one", "two".expandToValWithName("secondValue"))

    /**
      * @com.github.kczulko.ExtendScope together with 'expandToValWithName' have created
      * a new expression in existing code: val secondValue: String = "two"
      */
    assert(secondValue == "two")

    /**
      * This example shows that pattern matching
      * against Tree is type & expression agnostic
      */
    @ExtendScope
    val seq2 = Seq(1, 2, valueSupplier(3).expandToValWithName("third"))
    assert(third == 3)
  }

  def valueSupplier[T](v: T): T = v
}