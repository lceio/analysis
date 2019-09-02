package com.sm.analysis

import java.util.Properties

import org.apache.spark.sql.SparkSession

object TestDataFrame {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("sparksql").master("local").getOrCreate()

    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "863863")

    val url = "jdbc:mysql://localhost:3306/test"

    val df = spark.read.jdbc(url, "user_login", prop).where("id <= 100")

    //var array = df.collect()

    //var list = df.collectAsList()

    var count = df.count()
    println(count)

    spark.stop()
  }
}
