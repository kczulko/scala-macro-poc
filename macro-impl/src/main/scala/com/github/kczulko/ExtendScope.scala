package com.github.kczulko


import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

class ExtendScope extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro ExtendScope.impl
}

object ExtendScope {
  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val result = annottees.map(_.tree).toList match {
      case q"$mods val $tname: $tpt = $expr" :: Nil =>

        def extractVariableDefinition(list: List[c.Tree]): (Any, c.Tree) = {
          list match {
            case Apply(Select(v, TermName("expandToValWithName")), List(Literal(Constant(n)))) :: _ => (n, v)
            case _ :: t => extractVariableDefinition(t)
            case _ => ???
          }
        }

        val (name, value) = expr match {
          case Apply(Ident(TermName("Seq")), list) =>
            extractVariableDefinition(list)
          case _ => ???
        }

        q"""
           val ${TermName(name.toString)} = $value
           $mods val $tname: $tpt = $expr
         """
    }

    c.Expr(result)
  }
}
