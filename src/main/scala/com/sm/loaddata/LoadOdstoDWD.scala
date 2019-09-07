package com.sm.loaddata

import java.io.File

import com.sm.bean.{MoneyFlowBean, RoleRankBean}
import com.sm.conf.ConfigurationManager
import com.sm.constants.Constants
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

object LoadOdstoDwd {
  private val logger = LoggerFactory.getLogger("LogCollector")
  private val warehouseLocaion: String = new File("spark-warehouse").getAbsolutePath
  private val props = ConfigurationManager

  Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.spark-project.jetty").setLevel(Level.WARN)

  def main(args: Array[String]): Unit = {
    import org.apache.spark


    val conf = new SparkConf().registerKryoClasses(Array(classOf[MoneyFlowBean], classOf[RoleRankBean]))

    val spark = initSparkSession(conf)

    // 读取ODS层数据,解析导入DWD层事件表
    logger.info("=============================> 连接Hive,拉取ODS层数据 <=============================")

    val prop = ConfigurationManager.properties
    prop.load(ConfigurationManager.getClass.getClassLoader.getResourceAsStream("hive-ods.properties"))

    import spark.sql
    val importSql =
      s"""
         |select * from
         | ${prop.getProperty("hive.database")}.${prop.getProperty("hive.ods_cp_api_log")} limit 10
      """.stripMargin

    sql(importSql)



    spark.stop()
  }

  def importHiveOds: DataFrame = {

  }

  def initSparkSession(conf: SparkConf): SparkSession = SparkSession.builder().config(conf)
    .appName(Constants.SPARK_NAME).master(Constants.SPARK_LOCAL_MODE)
    .config("spark.sql.warehouse.dir", warehouseLocaion)
    .config("hive.exec.dynamic.partition", "true")
    .config("hive.exec.dynamic.partition.mode", "nonstrict")
    .config("hive.exec.max.dynamic.partitions", 2000)
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .config("spark.kryoserializer.buffer", "1024m")
    .config("spark.kryoserializer.buffer.max", "2046m")
    .config("spark.io.compression.codec", "snappy")
    .config("spark.sql.codegen", "true")
    .config("spark.sql.unsafe.enabled", "true")
    .config("spark.shuffle.manager", "tungsten-sort")
    .enableHiveSupport()
    .getOrCreate()
}