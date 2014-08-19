package com.github.tminglei.slickpg
package net

import scala.slick.ast.Library.{SqlFunction, SqlOperator}
import scala.slick.driver.{PostgresDriver, JdbcTypesComponent}
import scala.slick.lifted.{FunctionSymbolExtensionMethods, ExtensionMethods, Column}
import scala.slick.jdbc.JdbcType
import FunctionSymbolExtensionMethods._

trait PgNetExtensions extends JdbcTypesComponent { driver: PostgresDriver =>
  import driver.Implicit._

  object NetLibrary {
    val << = new SqlOperator("<<")
    val <<= = new SqlOperator("<<=")
    val >> = new SqlOperator(">>")
    val >>= = new SqlOperator(">>=")
    val && = new SqlOperator("&&")
    val ~ = new SqlOperator("~")
    val & = new SqlOperator("&")
    val | = new SqlOperator("|")
    val + = new SqlOperator("+")
    val - = new SqlOperator("-")

    val abbrev = new SqlFunction("abbrev")
    val broadcast = new SqlFunction("broadcast")
    val family = new SqlFunction("family")
    val host = new SqlFunction("host")
    val hostmask = new SqlFunction("hostmask")
    val masklen = new SqlFunction("masklen")
    val netmask = new SqlFunction("netmask")
    val network = new SqlFunction("network")
    val set_masklen = new SqlFunction("set_masklen")
    val text = new SqlFunction("text")
    val trunc = new SqlFunction("trunc")
  }

  class InetColumnExtensionMethods[B1, P1](val c: Column[P1])(
              implicit tm: JdbcType[B1]) extends ExtensionMethods[B1, P1] {
    def <<[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Boolean, R]) = {
        om.column(NetLibrary.<<, n, e.toNode)
      }
    def <<=[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Boolean, R]) = {
        om.column(NetLibrary.<<=, n, e.toNode)
      }
    def >>[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Boolean, R]) = {
        om.column(NetLibrary.>>, n, e.toNode)
      }
    def >>=[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Boolean, R]) = {
        om.column(NetLibrary.>>=, n, e.toNode)
      }
    def &&&[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Boolean, R]) = {
        om.column(NetLibrary.&&, n, e.toNode)
      }

    def unary_~ = NetLibrary.~.column[P1](n)
    def &[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[B1, R]) = {
        om.column(NetLibrary.&, n, e.toNode)
      }
    def |[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[B1, R]) = {
        om.column(NetLibrary.|, n, e.toNode)
      }
    def +(e: Column[Int]) = NetLibrary.+.column[P1](n, e.toNode)
    def -(e: Column[Int]) = NetLibrary.-.column[P1](n, e.toNode)
    def --[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[Int, R]) = {
        om.column(NetLibrary.-, n, e.toNode)
      }

    def abbrev[R](implicit om: o#to[String, R], tm: JdbcType[R]) = NetLibrary.abbrev.column[R](n)
    def broadcast[R](implicit om: o#to[B1, R], tm: JdbcType[R]) = NetLibrary.broadcast.column[R](n)
    def family[R](implicit om: o#to[Int, R], tm: JdbcType[R]) = NetLibrary.family.column[R](n)
    def host[R](implicit om: o#to[String, R], tm: JdbcType[R]) = NetLibrary.host.column[R](n)
    def hostmask[R](implicit om: o#to[B1, R], tm: JdbcType[R]) = NetLibrary.hostmask.column[R](n)
    def masklen[R](implicit om: o#to[Int, R], tm: JdbcType[R]) = NetLibrary.masklen.column[R](n)
    def netmask[R](implicit om: o#to[B1, R], tm: JdbcType[R]) = NetLibrary.netmask.column[R](n)
    def network[R](implicit om: o#to[B1, R], tm: JdbcType[R]) = NetLibrary.network.column[R](n)
    def text[R](implicit om: o#to[String, R], tm: JdbcType[R]) = NetLibrary.text.column[R](n)
    def setMasklen(e: Column[Int]) = NetLibrary.set_masklen.column[P1](n, e.toNode)
  }

  class MacAddrColumnExtensionMethods[B1, P1](val c: Column[P1])(
              implicit tm: JdbcType[B1]) extends ExtensionMethods[B1, P1] {
    def unary_~ = NetLibrary.~.column[P1](n)
    def &[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[B1, R]) = {
        om.column(NetLibrary.&, n, e.toNode)
      }
    def |[P2, R](e: Column[P2])(implicit om: o#arg[B1, P2]#to[B1, R]) = {
        om.column(NetLibrary.|, n, e.toNode)
      }

    def trunc = NetLibrary.trunc.column[P1](n)
  }
}
