package com.sm.constants

object Constants2 {
//  val SPARK_MODE = "yarn"
  val SPARK_MODE = "local[2]"

  val SPARK_NAME =s"${this.getClass.getSimpleName}"

  //val NUM_CORES = Math.abs(96 * 0.75).toInt

  // database
  var ORIGIN_PLATFORM = "origin_platform.properties"
  var BASE_CONF_PLATFORM = "base_conf_platform"
  var USER_NEW_LOGIN = "user_new_login"
  var PY_PLATFORM = "py_platform"
  var TEST="test.properties"
}
