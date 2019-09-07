package com.sm.loaddata

import java.io.File
import java.util.Properties

import com.sm.bean.{MoneyFlowBean, RoleRankBean}
import com.sm.conf.ConfigurationManager
import com.sm.constants.Constants
import com.sm.utils.DateUtils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import org.slf4j.LoggerFactory

object LoadOdsToDwd {
  private val warehouseLocaion: String = new File("spark-warehouse").getAbsolutePath
  private val logger = LoggerFactory.getLogger("LoadOdsToDwd")
  var fTable = ""
  var destTable = ""
  var table = ""
  var url = ""

  def main(args: Array[String]): Unit = {
//
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.spark-project.jetty").setLevel(Level.WARN)

    // 获取当天和前一天日期
    var today = DateUtils.getTodayDate
    var yesterday = DateUtils.getYesterdayDate

    if(args.length == 2 ){
      yesterday = args(0)
      today = args(1)
    }

    val conf = new SparkConf().registerKryoClasses(Array(classOf[MoneyFlowBean],classOf[RoleRankBean]))

    val spark = initSparkSession(conf)

    // 读取ODS层数据,解析导入DWD层事件表
    logger.info("===================> 开始读取Hive ODS层数据 <===================")

    ConfigurationManager.properties.load(ConfigurationManager.getClass.getClassLoader.getResourceAsStream("hive-ods.properties"))
    val props = ConfigurationManager.properties

    import spark.sql

    val importSql =
      s"""
        |select * from
        | ${props.getProperty("hive.database")}.${props.getProperty("hive.ods.cp.api.temp.table")} limit 10
      """.stripMargin
    logger.info(importSql)

    logger.info("===================> 加载temp表 导入ODS层 <===================")
//    sql(importSql).collect().foreach(println)
    sql(importSql).write.format("orc").insertInto("sm_data.ods_cp_api_log2")


    spark.stop()
  }

  def initSparkSession(conf: SparkConf):SparkSession = SparkSession.builder().config(conf)
    .appName(Constants.SPARK_NAME)
    .master(Constants.SPARK_LOCAL_MODE)
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
}
