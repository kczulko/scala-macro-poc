# scala-macro-poc
Small proof of concept based on scala macros

## Motivation

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
      website.switchBackgroundColorTo(newColor)
      website backgroundColor shouldEqual newColor
    }
  }
}
```

The problem described above is about a collection of values where one element (or even more...) within a collection is referenced by some other code elsewhere. I do not want to define a new value in preceeding line and put its reference into the collection... I'd like to have some kind of operator that would expand the sope of my `twice referenced` value.

First idea to achieve expected behavior is to use scala macro together with pattern-matching-variable-binding-operator (I'd like to see german word for this xD) `@` so that it could look like:

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

assert(greenColorDefinition == "green")
```

## Project execution

From project's root directory:
```
sbt macroUsage/run
```

It runs [Main.scala](macro-usage/src/main/scala/com/github/kczulko/Main.scala) from macroUsage project.

```
kczulko$ sbt macroUsage/run
[info] Loading global plugins from /Users/kczulko/.sbt/0.13/plugins
[info] Loading project definition from /Users/kczulko/Projects/scala-macro-poc/project
[info] Set current project to macro (in build file:/Users/kczulko/Projects/scala-macro-poc/)
[info] Compiling 1 Scala source to /Users/kczulko/Projects/scala-macro-poc/macro-usage/target/scala-2.12/classes...
[info] Running com.github.kczulko.Main
So it works :)
[success] Total time: 4 s, completed 2017-03-19 12:47:50
```
