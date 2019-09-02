package com.sm.constants

object DeployMode extends Enumeration {
  type DeployMode = Value
  val LOCAL,TEST, PRODUCTION = Value
}