package com.sm.config

import java.io.InputStream
import java.util.Properties

import com.sm.constants.{Constants, DeployMode}
import com.sm.constants.DeployMode.DeployMode


/**
  * 配置文件的管理器，用于加载主要的配置信息，获取对应的配置项
  * 单例
  */
object ConfigurationManager {
  val properties = new Properties
  var dMode: DeployMode = _
  properties.load(ConfigurationManager.getClass.getClassLoader.getResourceAsStream("conf.properties"))
  val modeStr: String = properties.getProperty(Constants.SPARK_JOB_DEPLOY_MODE)
  dMode = DeployMode.withName(modeStr.toUpperCase)
  println(dMode)

  def getProperty(key: String): String = {
    properties.getProperty(key, "-1")
  }

  def getIntProperty(key: String): Integer = {
    Integer.valueOf(properties.getProperty(key, "-1"))
  }

  def getLongProperty(key: String): Long = {
    properties.getProperty(key, "-1").toLong
  }

  def getDoubleProperty(key: String): Double = {
    properties.getProperty(key, "-1").toDouble
  }

  def getBooleanProperty(key: String): Boolean = {
    properties.getProperty(key, "-1").toBoolean
  }

  /**
    * 为当前的配置文件追加对应的配置信息
    */
  def addProperties(in: InputStream): Unit = {
    properties.load(in)
    println("url:" + properties.getProperty("url"))
  }

  def addProperties(prop: Properties): Unit = {
    properties.putAll(prop)
  }
}
