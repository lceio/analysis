package com.sm.loaddata

import java.io.File
import java.util.Properties

import com.sm.conf.ConfigurationManager
import com.sm.constants.Constants2
import org.apache.spark.sql.{DataFrame, SparkSession}

object LoadOdstoDWD {
  val warehouseLocaion = new File("spark-warehouse").getAbsolutePath
  private val props = ConfigurationManager
  var fTable = ""
  var destTable = ""
  var table = ""
  var url = ""

  def main(args: Array[String]): Unit = {
    var spark = initSparkSession

    println("开始导入数据>>>")

    // 读取ODS层数据,解析导入DWD层事件表
    // 获取登录数最大ID
    val prop = props.getProperties(Constants2.TEST)
    table = prop.getProperty("table")
    url = prop.getProperty("url")
    println(prop)
    val maxId =
      """
        | select
        | max(id)
        | from tmpTable
      """.stripMargin
    getData(spark,maxId,prop)

    spark.stop()
  }

  def initSparkSession:SparkSession = SparkSession.builder()
    .appName(Constants2.SPARK_NAME)
    .master(Constants2.SPARK_MODE)
    .config("spark.sql.warehouse.dir", warehouseLocaion)
    .config("hive.exec.dynamic.partition", "true")
    .config("hive.exec.dynamic.partition.mode", "nonstrict")
    .config("hive.exec.max.dynamic.partitions", 2000)
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .config("spark.kryoserializer.buffer", "1024m")
    .config("spark.kryoserializer.buffer.max", "2046m")
    .config("spark.io.compression.codec","snappy")
    .config("spark.sql.codegen", "true")
    .config("spark.sql.unsafe.enabled", "true")
    .config("spark.shuffle.manager", "tungsten-sort")
    .enableHiveSupport()
    .getOrCreate()

  //获取配置数据
  //  def getProperties(db:String):Properties={
  //    val in = this.getClass.getClassLoader.getResourceAsStream(db)
  //    prop.load(in)
  //    table = prop.getProperty("table")
  //    url = prop.getProperty("url") + "?user="+prop.getProperty("user")+"&password="+prop.getProperty("password")
  //    prop
  //  }

  //连接数据库读取数据
  //  def getData(spark:SparkSession,sql:String):DataFrame={
  //    val dataDF = spark.sqlContext.read.format("jdbc").option("url",url).option("dbtable",table).load()
  //    dataDF.createOrReplaceTempView("dataTable")
  //    val dataFrame = spark.sql(sql)
  //    dataFrame
  //  }
  def getData(spark:SparkSession,sql:String,prop:Properties):DataFrame={
    val dataDF = spark.sqlContext.read.format("jdbc").option("url",url).option("dbtable",table).load()
    dataDF.createOrReplaceTempView("tmpTable")
    val dataFrame = spark.sql(sql)
    dataFrame
  }
}
