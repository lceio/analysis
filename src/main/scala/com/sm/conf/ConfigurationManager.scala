package com.sm.conf

import java.util.Properties


object ConfigurationManager{

  var properties:Properties = new Properties()

  def getProperties(db: String): Properties = {
    val in = this.getClass.getClassLoader.getResourceAsStream(db)
    properties.load(in)
    val url = properties.getProperty("url") + "?user="+ properties.getProperty("user")+"&password="+ properties.getProperty("password")
    properties.setProperty("url",url)
    properties
  }
}

