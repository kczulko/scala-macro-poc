import scala.reflect.runtime.universe._

val Apply(Ident(TermName("Seq" | "List")), l) = q"""Seq("one")"""
val Apply(Select(value, TermName("asInjectedVal")), List(Literal(Constant(name)))) = q""" "two".asInjectedVal("test") """