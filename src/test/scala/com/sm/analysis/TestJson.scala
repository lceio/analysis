package com.sm.analysis

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.{JSON, JSONObject, JSONPObject}
import com.sm.bean.EventBean
import com.sm.logcollector.LogCollector.rand

import scala.util.Random

object TestJson {
  private val rand = new Random()
  def main(args: Array[String]): Unit = {
    val eventBean = EventBean()
    eventBean.event_time = System.currentTimeMillis - rand.nextInt(99999999) + ""
    eventBean.event_name = "role_rank"
    val eJson =  JSON.toJSONString(eventBean,SerializerFeature.WriteMapNullValue)
    val eObject: JSONObject = JSON.parseObject(eJson)
    println(eObject)
  }
}
