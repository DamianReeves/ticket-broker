package com.github.damianreeves.ticketbroker.common.model.domain.actions

sealed trait BulkAction {

}

object BulkAction {

  case class BulkUpdateAction() extends BulkAction
  case class BulkInsertAction() extends BulkAction
}
