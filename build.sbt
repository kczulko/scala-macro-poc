name := "macro"

version := "1.0"

val globalScalaVersion = "2.12.0"
val paradiseVersion = "2.1.0"

lazy val macroImpl = (project in new File("macro-impl"))
  .settings(
    name := "macro-impl",
    scalaVersion := globalScalaVersion,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % globalScalaVersion % "test",
      "org.scala-lang" % "scala-reflect" % globalScalaVersion
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
  )
lazy val macroUsage = (project in new File("macro-usage"))
    .settings(name := "macro-usage",
      scalaVersion := globalScalaVersion,
      libraryDependencies ++= Seq("org.scala-lang" % "scala-reflect" % globalScalaVersion),
      addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
    )
    .dependsOn(macroImpl)

lazy val root = (project in new File("."))
    .settings(name:="macro-project")
    .aggregate(macroImpl, macroUsage)


