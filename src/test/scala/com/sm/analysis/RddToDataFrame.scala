package com.sm.analysis

import org.apache.spark.sql.SparkSession

object RddToDataFrame {
  case class Words(id: Long, name: String) extends Serializable
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("TestDataFrame").master("local").getOrCreate()

    //     1. 从已有RDD创建
    val rdd = spark.sparkContext.textFile("file:///d:/data/words.txt")
    //      .persist(StorageLevel.MEMORY_ONLY)
    //
    //    val rdd2 = rdd.flatMap(_.split(",")).distinct().zipWithIndex().map(t =>{Row(t._2,t._1)})
    //
    //    val schema = StructType{
    //      List(
    //        StructField("id",LongType,true),
    //        StructField("user",StringType,true)
    //      )}
    //
    //    val DF1 = spark.createDataFrame(rdd2,schema).show()
    //
    ////     2. 自动推断
    //    val DF2 = rdd.map{
    //      x => {
    //        val tmp =  x.split(",")
    //        (tmp(0).toInt, tmp(1))
    //      }
    //    }
    //      //.toDF("id","name")

    //     3. 反射获取case类属性映射成表结构
//    val DF3 = rdd.map {
//      x => {
//        val tmp = x.split(",")
//        Words(tmp(0).toInt, tmp(1))
//      }
//    }.toDF().show()
    //
    //     4. 从Spark读取的数据源创建
    //    val prop = new Properties()
    //    prop.put("user", "root")
    //    prop.put("password", "863863")
    //
    //    val url = "jdbc:mysql://localhost:3306/test"
    //
    //    val DF3 = spark.read.jdbc(url, "user_login", prop).where("id <= 90000")

    // 5. DataFrame转RDD
    //        val DF = spark.read.text("file:///d:/data/words.txt")
    //
    //        val rdd = DF.rdd
    //    val rdd2: RDD[String] = rdd.map( _(0).toString)
    //        val rdd3: RDD[String] = rdd.map(_.getAs[String](0))
    //        val rdd4: RDD[String] = rdd.map(_.get(0).toString)

    //    import spark.implicits._
    // 1. createDateset
    //    val rdd2 = rdd.map(_.split(",")).map(x => (x(0), x(1)))
    //
    //    val DF1 = spark.createDataset(rdd2).show()

    //     2. 自动推断
    //    val DS = rdd.map( x => {val tmp = x.split(",");(tmp(0).toInt, tmp(1))}).toDS()

    //     2. case
    //    val DS = rdd.map {
    //      x => {
    //        val tmp = x.split(",")
    //        Words(tmp(0).toInt, tmp(1))
    //      }
    //    }.toDS()
    //
    //    val rdd2: RDD[(Int, String)] = DS.rdd

    spark.stop()
  }

  //  def initSparkSession:SparkSession = SparkSession.builder()
  //    .appName(Constants.SPARK_NAME)
  //    .master(Constants.SPARK_MODE)
  //    .config("spark.sql.warehouse.dir", warehouseLocaion)
  //    .config("hive.exec.dynamic.partition", "true")
  //    .config("hive.exec.dynamic.partition.mode", "nonstrict")
  //    .config("hive.exec.max.dynamic.partitions", 2000)
  //    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
  //    .config("spark.kryoserializer.buffer", "1024m")
  //    .config("spark.kryoserializer.buffer.max", "2046m")
  //    .config("spark.io.compression.codec","snappy")
  //    .config("spark.sql.codegen", "true")
  //    .config("spark.sql.unsafe.enabled", "true")
  //    .config("spark.shuffle.manager", "tungsten-sort")
  //    .getOrCreate()
}
