package com.github.damianreeves.ticketbroker.common.model.dto

sealed trait QueryOperation[A]

object QueryOperation {
  case class In[A](set:Set[A]) extends QueryOperation[A]
  case class Equals[A](value:A) extends QueryOperation[A]
  case class StartsWith(prefix:String) extends QueryOperation[String]
}
