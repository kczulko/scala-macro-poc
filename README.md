# scala-macro-poc
Small proof of concept based on scala macros

## motivation

Many times I've encountered a situation like this:

```scala
class TestClass extends FlatSpec with Matchers {
  val greenColorDefinition = "green"
  val colors = Seq("yellow", greenColorDefinition, "purple")

  "website" should "change background color when green button clicked" in {
    website.greenButton.click
    website color shouldEqual greenColorDefinition
  }

  it should "switch color to one selected by user" in {
    colors foreach { newColor =>
      website switchTo color
      website color shouldEqual newColor
    }
  }
}
```

The problem described above is about a collection of variables where one of the elements within a collection is referenced by some other code somewhere else. I do not want to define variable somewhere above and put it into the collection... I'd like to have some kind of operator that would expand my `twice referenced` variable scope.

First idea is to do this by a scala macro with and use pattern-matching-variable-binding operator `@`:

```scala
val colors = Seq("yellow", greenColorDefinition @ "green", "purple")
```

but this solution is probably impossible due to Eugene Burmako answer here:
http://stackoverflow.com/questions/18846782/how-to-create-variables-with-macros-in-scala

So inspired by this answer I did sth like that:

```scala
import com.github.kczulko.Marker.Expand

@ExtendScope
val colors = Seq("yellow", "green".expandToValWithName("greenColorDefinition"), "purple")
```
