package com.sm.utils

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkUtil {

  def getSparkConf(master: String, appName: String): SparkConf = {
    new SparkConf().setMaster(master).setAppName(appName)
  }

  def getSparkSession(conf:SparkConf): SparkSession = {
    SparkSession.builder().config(conf).getOrCreate()
  }

  def getSparkContext(conf: SparkConf): SparkContext = {
    getSparkSession(conf).sparkContext
  }

  def getSQLContext(conf: SparkConf): SQLContext = {
    getSparkSession(conf).sqlContext
  }
}
