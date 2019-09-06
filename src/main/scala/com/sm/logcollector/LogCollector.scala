package com.sm.logcollector

import java.util

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.sm.bean.{EventBean, MoneyFlowBean, RoleRankBean}
import org.slf4j.LoggerFactory

import scala.util.Random

/**
  * 模拟数据生成类
  * args 0  生成每条的延迟,默认0
  * args 1  生成条数,默认1000
  */

object LogCollector {
  private val logger = LoggerFactory.getLogger("LogCollector")
  private val rand = new Random()

  def main(args: Array[String]): Unit = {

    // 参数一：控制发送每条的延时时间，默认0
    val delay = if (args.length > 0) args(0).toLong else 0L

    // 参数二：循环遍历次数
    val loop_len = if (args.length > 1) args(1).toInt else 100

    // 生成数据
    //    generateLog(delay, loop_len)

    generateLog(0, 100)


  }

  /**
    * 生成数据方法
    */
  def generateLog(delay: Long, loop_len: Int): Unit = {

    for (i <- 0 to loop_len) {

      print(i + "======>")

      val flag = rand.nextInt(2)

      flag match {
        case 0 =>
          val eventsArray = new JSONArray
          if (rand.nextBoolean) {
            // 角色升级
            eventsArray.add(getRoleRank)
          } else {
            // 角色金钱改变
            eventsArray.add(getMoneyFlow)
          }
          //控制台打印
          logger.info(eventsArray.toJSONString)
        case 1 =>
          val eventsArray = new JSONArray
          if (rand.nextBoolean) {
            // 角色升级
            eventsArray.add(getRoleRank)
          } else {
            // 角色金钱改变
            eventsArray.add(getMoneyFlow)
          }
          //控制台打印
          logger.info(eventsArray.toJSONString)
      }
      // 延迟
      try
        Thread.sleep(delay)
      catch {
        case e: InterruptedException =>
          e.printStackTrace()
      }
    }
  }


  /**
    * 设置事件,并拼接所有属性(game_id,事件,角色信息)
    */
  def packEventJson(eventName: String, json: JSON):JSONObject = {
    val packJson = new JSONObject(true)

    // 拼接cp_game_id
    val cp_game_id = rand.nextInt(19)

    // 拼接事件
    val eventBean = EventBean()
    eventBean.event_time = System.currentTimeMillis - rand.nextInt(99999999) + ""
    eventBean.event_name = eventName

    val eventJson = JSON.toJSON(eventBean).asInstanceOf[JSONObject]


    packJson.put("cp_game_id", cp_game_id)
    packJson.put("event", eventJson)
    packJson.put("data", json)

    packJson
  }

  /**
    * 角色升级事件
    */
  def getRoleRank: JSONObject = {
    val roleRank = RoleRankBean()
    roleRank.game_server = rand.nextInt(9999)
    roleRank.role_id = rand.nextInt(99999).toString
    roleRank.role_name = getContent
    roleRank.role = getRole
    roleRank.school = getSchool
    roleRank.combat = rand.nextInt(99999)
    roleRank.role_vip = rand.nextInt(10)
    roleRank.before_rank = rand.nextInt(199)
    roleRank.role_rank = roleRank.before_rank + rand.nextInt(5)

    val roleJson = JSON.toJSON(roleRank).asInstanceOf[JSONObject]

    packEventJson("role_rank", roleJson)
  }

  /**
    * 角色金钱改变事件
    */
  def getMoneyFlow: JSONObject = {
    val moneyFlow = MoneyFlowBean()
    moneyFlow.game_server = rand.nextInt(9999)
    moneyFlow.role_id = rand.nextInt(99999).toString
    moneyFlow.role_name = getContent
    moneyFlow.role = getRole
    moneyFlow.school = getSchool
    moneyFlow.combat = rand.nextInt(99999)
    moneyFlow.role_vip = rand.nextInt(10)
    moneyFlow.role_rank = rand.nextInt(199)
    moneyFlow.item_id = getItem
    moneyFlow.count = rand.nextInt(100)
    moneyFlow.after_count = rand.nextInt(1000)
    moneyFlow.money_type = getMoneyType
    moneyFlow.money = rand.nextInt(9999)
    moneyFlow.after_money = rand.nextInt(99999)
    moneyFlow.add_or_rduce = {
      val i = rand.nextInt(1)
      if (i == 0) 0 else 1
    }
    moneyFlow.reason = getReason
    moneyFlow.sub_reason = getSubReason

    val moneyJson = JSON.toJSON(moneyFlow).asInstanceOf[JSONObject]

    packEventJson("money_Flow", moneyJson)
  }


  /**
    * 生成单个汉字
    */
  def getRandomChar: Char = {
    var str: String = ""
    var highPos: Int = 0
    var lowPos: Int = 0

    // 随机生成汉字的两个字节
    highPos = Math.abs(rand.nextInt(39)) + 176
    lowPos = Math.abs(rand.nextInt(93)) + 161

    val bys = new Array[Byte](2)
    bys(0) = highPos.toByte
    bys(1) = lowPos.toByte

    try {
      str = new String(bys, "GBK")
    } catch {
      case e: UnsupportedOperationException =>
        e.printStackTrace()
        println("编码错误")
    }

    str.charAt(0)
  }

  /**
    * 拼接多个汉字
    */
  def getContent: String = {
    val stringBuffer = new StringBuffer
    for (i <- 0 until rand.nextInt(6)) {
      stringBuffer.append(getRandomChar)
    }
    stringBuffer.toString
  }

  /**
    * 职业
    */
  def getRole: String = {
    var role: String = ""
    val array = Array("法师", "狂暴战士", "刺客")
    role = array(rand.nextInt(3))
    role
  }

  /**
    * 门派
    */
  def getSchool: String = {
    var school: String = ""
    val array = Array("玄策", "隐士", "唐门")
    school = array(rand.nextInt(3))
    school
  }

  /**
    * 物品名称
    */
  def getItem: String = {
    var item: String = ""
    val array = Array("草药", "装备", "神器")
    item = array(rand.nextInt(3))
    item
  }

  /**
    * 变动时金钱的类型
    */
  def getMoneyType: String = {
    var moneyType: String = ""
    val array = Array("钻石", "金币", "铜币")
    moneyType = array(rand.nextInt(3))

    moneyType
  }

  /**
    * 金钱流动原因
    */
  def getReason: String = {
    var reason: String = ""
    val array = Array("商城道具", "邮件领取奖励", "战斗消耗")
    reason = array(rand.nextInt(3))
    reason
  }

  /**
    * 金钱流动原因
    */
  def getSubReason: String = {
    var subReason: String = ""
    val array = Array("道具消耗", "购买宠物", "战斗药水消耗")
    subReason = array(rand.nextInt(3))
    subReason
  }
}
