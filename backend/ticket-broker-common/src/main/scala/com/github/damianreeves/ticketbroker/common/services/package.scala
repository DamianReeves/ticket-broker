package com.github.damianreeves.ticketbroker.common

package object services {
  object profileManager extends ProfileManager.Syntax
  object configProvider extends ConfigProvider.Syntax
  object webServer extends WebServer.Syntax
}
