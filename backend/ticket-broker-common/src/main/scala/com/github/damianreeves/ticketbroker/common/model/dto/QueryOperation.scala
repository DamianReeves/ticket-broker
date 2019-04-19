package com.github.damianreeves.ticketbroker.common.model.dto

sealed trait QueryOperation[A]

object QueryOperation {
  case class In[A](set:Set[A]) extends QueryOperation[A]
  object In {
    def apply[A](values: Seq[A]): In[A] = new In(values.toSet)
    def apply[A](values: A*): QueryOperation[A] = apply(values)
  }
  case class Equals[A](value:A) extends QueryOperation[A]
  case class StartsWith(prefix:String) extends QueryOperation[String]
}
