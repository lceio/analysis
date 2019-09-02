package com.sm.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}

object DateUtils {
  //获取日期 格式2018-09-27 13:39:35
  //参数i :  当天为0，前一天为-1
  def getDate(i:Int):String={
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE,i)
    var day = timestampToDate(cal.getTimeInMillis)
    day
  }

  /**
    * 格式化日期
    * @param s 2018-12-12 12:11:10
    * @return 20181212
    */
  def fmtDate(s:String):Option[String]={
    try {
      if (StringUtils.isNotEmpty(s)) {
        val fields = s.split(" ")
        if (fields.length > 1) {
          Some(fields(0).replace("-", ""))
        } else {
          None
        }

      } else {
        None
      }
    } catch {
      case _:Exception => None
    }
  }

  /**
    * 格式化日期 string
    * @param s 2018-12-12 12:11:10
    * @return 20181212
    */
  def formatDate(s:String):String= {
    var date = ""
    if (StringUtils.isNotEmpty(s)) {
      val fields = s.split(" ")
      if (fields.length > 1) {
        date = fields(0).replace("-", "")
      }
    }
    date
  }

  /**
    * 将GMT日期格式转换为时间戳
    * @param s 12/Sep/2018:00:07:39 +0800
    * @return timestamp
    */
  def gmtDateToTimestamp(s : String):Long = {
    var date:Long = 0
    if (StringUtils.isNotEmpty(s)) {
      date = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss",Locale.ENGLISH).parse(s).getTime
    }
    date
  }

  /**
    * 将时间格式化为时间戳
    * @param s 2018-12-12 16:08:31
    * @return timestamp
    */
  def timeToTimestamp(s:String):Long={
    val date = new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime
    date
  }

  /**
    * 将CMT日期格式转换为普通日期
    * @param s 12/Sep/2018:00:07:39 +0800
    * @return 2018-12-12 12:11:10
    */
  def gmtDateToTime(s : String):String = {
    var date = ""
    if (StringUtils.isNotEmpty(s)) {
      date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s)
    }
    date.toString
  }

  /**
    * 将时间戳转换为日期格式
    * @param s timestamp
    * @return 2018-12-12 12:11:10
    */
  def timestampToTime(s : Long):String={
    val date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s)
    date
  }

  /**
    * 将时间戳转换为日期格式,格式化到天
    * @param s XXXXX
    * @return 2018-12-12
    */
  def timestampToDate(s:Long):String={
    val date = new SimpleDateFormat("yyyy-MM-dd").format(s)
    date
  }

  /**
    * 将时间格式化为0 时整的时间
    * @param s 2018-12-12 16:08:31
    * @return 2018-12-12 00:00:00
    */
  def timestampToDateZ(s:String):String={
    val time = new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime
    val date = timestampToTime(time)
    date
  }

  /**
    * 将时间转换为日期格式,格式化到月
    * @param s 2018-12-12 12:11:10
    * @return 2018-12
    */
  def dateToMonth(s:String):String={
    var date = ""
    if (StringUtils.isNotEmpty(s)) {
      val fields = s.split(" ")(0).split("-")
      date = fields(0)+fields(1)
    }
    date
  }

  /**
    * 格式化小时
    * @param s 2018-01-13 12:11:10
    * @return 12
    */
  def fmtHour(s:String):Option[String]={
    try {
      if (StringUtils.isNotEmpty(s)) {
        val fields = s.split(" ")
        if (fields.length > 1) {
          Some(fields(1).substring(0, 2))
        } else {
          None
        }

      } else {
        None
      }
    } catch {
      case _:Exception => None
    }
  }

  def main(args: Array[String]): Unit = {
    //    val date = fmtDate("2018-01-13 12:11:10").getOrElse("unkown")
    //    val hour = fmtHour("2018-01-13 12:11:10").getOrElse("unkown")
    //    print(date + "  "+ hour)
    //println(getId())
    //    val date = gmtDateToTimestamp("11/Sep/2018:00:01:51")
    //    println(date)
    //    val date2 = timestampToTime(date)
    //    println(date2)

    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE,-3)
    var day = DateUtils.timeToTimestamp(DateUtils.timestampToTime(cal.getTimeInMillis))
    println(day)
  }
}
