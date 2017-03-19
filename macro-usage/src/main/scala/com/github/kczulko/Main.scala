package com.github.kczulko

object Main {

  import com.github.kczulko.Marker.Expand

  def main(args: Array[String]): Unit = {

    /**
      * @com.github.kczulko.ExtendScope together with 'expandToValWithName' have created
      * a new expression in existing code: val secondValue: String = "two"
      */
    @ExtendScope
    val strings = Seq("one", "two".expandToValWithName("secondValue"))
    assert(secondValue == "two")

    /**
      * This example shows that pattern matching
      * against Tree is type & expression agnostic
      */
    @ExtendScope
    val digits = Seq(1, 2, valueSupplier(3).expandToValWithName("third"))
    assert(third == 3)
  }

  def valueSupplier[T](v: T): T = v
}
