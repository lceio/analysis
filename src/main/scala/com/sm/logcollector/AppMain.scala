//package com.sm.logcollector
//
//import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
//import java.io.UnsupportedEncodingException
//
//import com.alibaba.fastjson.serializer.SerializerFeature
//import com.sm.bean.{AppActive_background, AppActive_foreground, AppAd, AppBase, AppComment, AppDisplay, AppErrorLog, AppFavorites, AppLoading, AppNewsDetail, AppNotification, AppPraise, AppStart}
//import org.slf4j.LoggerFactory
//
//import scala.util.Random
//
//object AppMain {
//  private val logger = LoggerFactory.getLogger("AppMain")
//  private val rand = new Random()
//  // 设备id
//  private var s_mid = 0
//  // 用户id
//  private var s_uid = 0
//  // 商品id
//  private var s_goodsid = 0
//
//  def main(args: Array[String]): Unit = { // 参数一：控制发送每条的延时时间，默认是0
//    val delay = if (args.length > 0) args(0).toLong
//    else 0L
//    // 参数二：循环遍历次数
//    val loop_len = if (args.length > 1) args(1).toInt
//    else 1000
//    // 生成数据
//    generateLog(delay, loop_len)
//  }
//
//  private def generateLog(delay: Long, loop_len: Int): Unit = {
//    var i = 0
//    while ( {
//      i < loop_len
//    }) {
//      val flag = rand.nextInt(2)
//      flag match {
//        case (0) =>
//          //应用启动
//          val appStart = generateStart
//          val jsonString = JSON.toJSONString(appStart,SerializerFeature.WriteMapNullValue)
//          //控制台打印
//          logger.info(jsonString)
//        case (1) =>
//          val json = new JSONObject
//          json.put("ap", "app")
//          json.put("cm", generateComFields)
//          val eventsArray = new JSONArray()
//          // 事件日志
//          // 商品点击，展示
//          if (rand.nextBoolean) {
//            eventsArray.add(generateDisplay)
//            json.put("et", eventsArray)
//          }
//          // 商品详情页
//          if (rand.nextBoolean) {
//            eventsArray.add(generateNewsDetail)
//            json.put("et", eventsArray)
//          }
//          // 商品列表页
//          if (rand.nextBoolean) {
//            eventsArray.add(generateNewList)
//            json.put("et", eventsArray)
//          }
//          // 广告
//          if (rand.nextBoolean) {
//            eventsArray.add(generateAd)
//            json.put("et", eventsArray)
//          }
//          // 消息通知
//          if (rand.nextBoolean) {
//            eventsArray.add(generateNotification)
//            json.put("et", eventsArray)
//          }
//          // 用户前台活跃
//          if (rand.nextBoolean) {
//            eventsArray.add(generatbeforeground)
//            json.put("et", eventsArray)
//          }
//          // 用户后台活跃
//          if (rand.nextBoolean) {
//            eventsArray.add(generateBackground)
//            json.put("et", eventsArray)
//          }
//          //故障日志
//          if (rand.nextBoolean) {
//            eventsArray.add(generateError)
//            json.put("et", eventsArray)
//          }
//          // 用户评论
//          if (rand.nextBoolean) {
//            eventsArray.add(generateComment)
//            json.put("et", eventsArray)
//          }
//          // 用户收藏
//          if (rand.nextBoolean) {
//            eventsArray.add(generateFavorites)
//            json.put("et", eventsArray)
//          }
//          // 用户点赞
//          if (rand.nextBoolean) {
//            eventsArray.add(generatePraise)
//            json.put("et", eventsArray)
//          }
//          //时间
//          val millis = System.currentTimeMillis
//          logger.info(millis + "|" + json.toJSONString)
//      }
//      // 延迟
//      try
//        Thread.sleep(delay)
//      catch {
//        case e: InterruptedException =>
//          e.printStackTrace()
//      }
//
//      {
//        i += 1; i - 1
//      }
//    }
//  }
//
//  /**
//    * 公共字段设置
//    */
//  private def generateComFields = {
//    val appBase = new AppBase
//    //设备id
//    appBase.setMid(s_mid + "")
//    s_mid += 1
//    appBase.setUid(s_uid + "")
//    s_uid += 1
//    // 程序版本号 5,6等
//    appBase.setVc("" + rand.nextInt(20))
//    //程序版本名 v1.1.1
//    appBase.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10))
//    // 安卓系统版本
//    appBase.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10))
//    // 语言  es,en,pt
//    var flag = rand.nextInt(3)
//    flag match {
//      case (0) =>
//        appBase.setL("es")
//      case (1) =>
//        appBase.setL("en")
//      case (2) =>
//        appBase.setL("pt")
//    }
//    // 渠道号   从哪个渠道来的
//    appBase.setSr(getRandomChar(1))
//    // 区域
//    flag = rand.nextInt(2)
//    flag match {
//      case 0 =>
//        appBase.setAr("BR")
//      case 1 =>
//        appBase.setAr("MX")
//    }
//    // 手机品牌 ba ,手机型号 md，就取2位数字了
//    flag = rand.nextInt(3)
//    flag match {
//      case 0 =>
//        appBase.setBa("Sumsung")
//        appBase.setMd("sumsung-" + rand.nextInt(20))
//      case 1 =>
//        appBase.setBa("Huawei")
//        appBase.setMd("Huawei-" + rand.nextInt(20))
//      case 2 =>
//        appBase.setBa("HTC")
//        appBase.setMd("HTC-" + rand.nextInt(20))
//    }
//    // 嵌入sdk的版本
//    appBase.setSv("V2." + rand.nextInt(10) + "." + rand.nextInt(10))
//    // gmail
//    appBase.setG(getRandomCharAndNumr(8) + "@gmail.com")
//    // 屏幕宽高 hw
//    flag = rand.nextInt(4)
//    flag match {
//      case 0 =>
//        appBase.setHw("640*960")
//      case 1 =>
//        appBase.setHw("640*1136")
//      case 2 =>
//        appBase.setHw("750*1134")
//      case 3 =>
//        appBase.setHw("1080*1920")
//    }
//    // 客户端产生日志时间
//    val millis = System.currentTimeMillis
//    appBase.setT("" + (millis - rand.nextInt(99999999)))
//    // 手机网络模式 3G,4G,WIFI
//    flag = rand.nextInt(3)
//    flag match {
//      case 0 =>
//        appBase.setNw("3G")
//      case 1 =>
//        appBase.setNw("4G")
//      case 2 =>
//        appBase.setNw("WIFI")
//    }
//    // 拉丁美洲 西经34°46′至西经117°09；北纬32°42′至南纬53°54′
//    // 经度
//    appBase.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "")
//    // 纬度
//    appBase.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "")
//    JSON.toJSON(appBase).asInstanceOf[JSONObject]
//  }
//
//  /**
//    * 商品展示事件
//    */
//  private def generateDisplay = {
//    val appDisplay = new AppDisplay
//    val boolFlag = rand.nextInt(10) < 7
//    // 动作：曝光商品=1，点击商品=2，
//    if (boolFlag) appDisplay.setAction("1")
//    else appDisplay.setAction("2")
//    val goodsId = s_goodsid + ""
//    s_goodsid += 1
//    appDisplay.setGoodsid(goodsId)
//    // 顺序  设置成6条吧
//    var flag = rand.nextInt(6)
//    appDisplay.setPlace("" + flag)
//    // 曝光类型
//    flag = 1 + rand.nextInt(2)
//    appDisplay.setExtend1("" + flag)
//    // 分类
//    flag = 1 + rand.nextInt(100)
//    appDisplay.setCategory("" + flag)
//    val jsonObject = JSON.toJSON(appDisplay).asInstanceOf[JSONObject]
//    packEventJson("display", jsonObject)
//  }
//
//  /**
//    * 商品详情页
//    */
//  private def generateNewsDetail = {
//    val appNewsDetail = new AppNewsDetail
//    // 页面入口来源
//    var flag = 1 + rand.nextInt(3)
//    appNewsDetail.setEntry(flag + "")
//    // 动作
//    appNewsDetail.setAction("" + (rand.nextInt(4) + 1))
//    appNewsDetail.setGoodsid(s_goodsid + "")
//    // 商品来源类型
//    flag = 1 + rand.nextInt(3)
//    appNewsDetail.setShowtype(flag + "")
//    // 商品样式
//    flag = rand.nextInt(6)
//    appNewsDetail.setShowtype("" + flag)
//    // 页面停留时长
//    flag = rand.nextInt(10) * rand.nextInt(7)
//    appNewsDetail.setNews_staytime(flag + "")
//    // 加载时长
//    flag = rand.nextInt(10) * rand.nextInt(7)
//    appNewsDetail.setLoading_time(flag + "")
//    // 加载失败码
//    flag = rand.nextInt(10)
//    flag match {
//      case 1 =>
//        appNewsDetail.setType1("102")
//      case 2 =>
//        appNewsDetail.setType1("201")
//      case 3 =>
//        appNewsDetail.setType1("325")
//      case 4 =>
//        appNewsDetail.setType1("433")
//      case 5 =>
//        appNewsDetail.setType1("542")
//      case _ =>
//        appNewsDetail.setType1("")
//    }
//    flag = 1 + rand.nextInt(100)
//    appNewsDetail.setCategory("" + flag)
//    val eventJson = JSON.toJSON(appNewsDetail).asInstanceOf[Nothing]
//    packEventJson("newsdetail", eventJson)
//  }
//
//  /**
//    * 商品列表
//    */
//  private def generateNewList = {
//    val appLoading = new AppLoading
//    var flag = rand.nextInt(3) + 1
//    appLoading.setAction(flag + "")
//    flag = rand.nextInt(10) * rand.nextInt(7)
//    appLoading.setLoading_time(flag + "")
//    // 失败码
//    flag = rand.nextInt(10)
//    flag match {
//      case 1 =>
//        appLoading.setType1("102")
//      case 2 =>
//        appLoading.setType1("201")
//      case 3 =>
//        appLoading.setType1("325")
//      case 4 =>
//        appLoading.setType1("433")
//      case 5 =>
//        appLoading.setType1("542")
//      case _ =>
//        appLoading.setType1("")
//    }
//    // 页面  加载类型
//    flag = 1 + rand.nextInt(2)
//    appLoading.setLoading_way("" + flag)
//    // 扩展字段1
//    appLoading.setExtend1("")
//    // 扩展字段2
//    appLoading.setExtend2("")
//    // 用户加载类型
//    flag = 1 + rand.nextInt(3)
//    appLoading.setType("" + flag)
//    val jsonObject = JSON.toJSON(appLoading).asInstanceOf[Nothing]
//    packEventJson("loading", jsonObject)
//  }
//
//  /**
//    * 广告相关字段
//    */
//  private def generateAd = {
//    val appAd = new AppAd
//    // 入口
//    var flag = rand.nextInt(3) + 1
//    appAd.setEntry(flag + "")
//    flag = rand.nextInt(5) + 1
//    appAd.setAction(flag + "")
//    // 状态
//    flag = if (rand.nextInt(10) > 6) 2
//    else 1
//    appAd.setContent(flag + "")
//    flag = rand.nextInt(10)
//    flag match {
//      case 1 =>
//        appAd.setDetail("102")
//      case 2 =>
//        appAd.setDetail("201")
//      case 3 =>
//        appAd.setDetail("325")
//      case 4 =>
//        appAd.setDetail("433")
//      case 5 =>
//        appAd.setDetail("542")
//      case _ =>
//        appAd.setDetail("")
//    }
//    // 广告来源
//    flag = rand.nextInt(4) + 1
//    appAd.setSource(flag + "")
//    // 用户行为
//    flag = rand.nextInt(2) + 1
//    appAd.setBehavior(flag + "")
//    // 商品类型
//    flag = rand.nextInt(10)
//    appAd.setNewstype("" + flag)
//    // 展示样式
//    flag = rand.nextInt(6)
//    appAd.setShow_style("" + flag)
//    val jsonObject = JSON.toJSON(appAd).asInstanceOf[Nothing]
//    packEventJson("ad", jsonObject)
//  }
//
//  /**
//    * 启动日志
//    */
//  private def generateStart = {
//    val appStart = new AppStart
//    appStart.setMid(s_mid + "")
//    s_mid += 1
//    appStart.setUid(s_uid + "")
//    s_uid += 1
//    appStart.setVc("" + rand.nextInt(20))
//    appStart.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10))
//    appStart.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10))
//    //设置日志类型
//    appStart.setEn("start")
//    //    语言  es,en,pt
//    var flag = rand.nextInt(3)
//    flag match {
//      case (0) =>
//        appStart.setL("es")
//      case (1) =>
//        appStart.setL("en")
//      case (2) =>
//        appStart.setL("pt")
//    }
//    appStart.setSr(getRandomChar(1))
//    flag = rand.nextInt(2)
//    flag match {
//      case 0 =>
//        appStart.setAr("BR")
//      case 1 =>
//        appStart.setAr("MX")
//    }
//    flag = rand.nextInt(3)
//    flag match {
//      case 0 =>
//        appStart.setBa("Sumsung")
//        appStart.setMd("sumsung-" + rand.nextInt(20))
//      case 1 =>
//        appStart.setBa("Huawei")
//        appStart.setMd("Huawei-" + rand.nextInt(20))
//      case 2 =>
//        appStart.setBa("HTC")
//        appStart.setMd("HTC-" + rand.nextInt(20))
//    }
//    appStart.setSv("V2." + rand.nextInt(10) + "." + rand.nextInt(10))
//    appStart.setG(getRandomCharAndNumr(8) + "@gmail.com")
//    flag = rand.nextInt(4)
//    flag match {
//      case 0 =>
//        appStart.setHw("640*960")
//      case 1 =>
//        appStart.setHw("640*1136")
//      case 2 =>
//        appStart.setHw("750*1134")
//      case 3 =>
//        appStart.setHw("1080*1920")
//    }
//    val millis = System.currentTimeMillis
//    appStart.setT("" + (millis - rand.nextInt(99999999)))
//    flag = rand.nextInt(3)
//    flag match {
//      case 0 =>
//        appStart.setNw("3G")
//      case 1 =>
//        appStart.setNw("4G")
//      case 2 =>
//        appStart.setNw("WIFI")
//    }
//    appStart.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "")
//    appStart.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "")
//    flag = rand.nextInt(5) + 1
//    appStart.setEntry(flag + "")
//    // 开屏广告类型
//    flag = rand.nextInt(2) + 1
//    appStart.setOpen_ad_type(flag + "")
//    flag = if (rand.nextInt(10) > 8) 2
//    else 1
//    appStart.setAction(flag + "")
//    appStart.setLoading_time(rand.nextInt(20) + "")
//    flag = rand.nextInt(10)
//    flag match {
//      case 1 =>
//        appStart.setDetail("102")
//      case 2 =>
//        appStart.setDetail("201")
//      case 3 =>
//        appStart.setDetail("325")
//      case 4 =>
//        appStart.setDetail("433")
//      case 5 =>
//        appStart.setDetail("542")
//      case _ =>
//        appStart.setDetail("")
//    }
//    // 扩展字段
//    appStart.setExtend1("")
//    appStart
//  }
//
//  /**
//    * 消息通知
//    */
//  private def generateNotification = {
//    val appNotification = new AppNotification
//    var flag = rand.nextInt(4) + 1
//    appNotification.setAction(flag + "")
//    // 通知id
//    flag = rand.nextInt(4) + 1
//    appNotification.setType(flag + "")
//    // 客户端弹时间
//    appNotification.setAp_time((System.currentTimeMillis - rand.nextInt(99999999)) + "")
//    // 备用字段
//    appNotification.setContent("")
//    val jsonObject = JSON.toJSON(appNotification).asInstanceOf[Nothing]
//    packEventJson("notification", jsonObject)
//  }
//
//  /**
//    * 前台活跃
//    */
//  private def generatbeforeground = {
//    val appActive_foreground = new AppActive_foreground
//    // 推送消息的id
//    var flag = rand.nextInt(2)
//    flag match {
//      case 1 =>
//        appActive_foreground.setAccess(flag + "")
//      case _ =>
//        appActive_foreground.setAccess("")
//    }
//    // 1.push 2.icon 3.其他
//    flag = rand.nextInt(3) + 1
//    appActive_foreground.setPush_id(flag + "")
//    val jsonObject = JSON.toJSON(appActive_foreground).asInstanceOf[Nothing]
//    packEventJson("active_foreground", jsonObject)
//  }
//
//  /**
//    * 后台活跃
//    */
//  private def generateBackground = {
//    val appActive_background = new AppActive_background
//    // 启动源
//    val flag = rand.nextInt(3) + 1
//    appActive_background.setActive_source(flag + "")
//    val jsonObject = JSON.toJSON(appActive_background).asInstanceOf[Nothing]
//    packEventJson("active_background", jsonObject)
//  }
//
//  /**
//    * 错误日志数据
//    */
//  private def generateError = {
//    val appErrorLog = new AppErrorLog
//    val errorBriefs = Array("at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)") //错误摘要
//    val errorDetails = Array("java.lang.NullPointerException\\n    " + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n") //错误详情
//    //错误摘要
//    appErrorLog.setErrorBrief(errorBriefs(rand.nextInt(errorBriefs.length)))
//    //错误详情
//    appErrorLog.setErrorDetail(errorDetails(rand.nextInt(errorDetails.length)))
//    val jsonObject = JSON.toJSON(appErrorLog).asInstanceOf[Nothing]
//    packEventJson("error", jsonObject)
//  }
//
//  /**
//    * 为各个事件类型的公共字段（时间、事件类型、Json数据）拼接
//    */
//  private def packEventJson(eventName: String, jsonObject: Nothing) = {
//    val eventJson = new JSONObject()
//    eventJson.put("ett", (System.currentTimeMillis - rand.nextInt(99999999)) + "")
//    eventJson.put("en", eventName)
//    eventJson.put("kv", jsonObject)
//    eventJson
//  }
//
//  /**
//    * 获取随机字母组合
//    *
//    * @param length 字符串长度
//    */
//  private def getRandomChar(length: Integer) = {
//    val str = new StringBuilder
//    val random = new Random
//    var i = 0
//    while ( {
//      i < length
//    }) { // 字符串
//      str.append((65 + random.nextInt(26)).asInstanceOf[Char]) // 取得大写字母
//
//
//      {
//        i += 1; i - 1
//      }
//    }
//    str.toString
//  }
//
//  /**
//    * 获取随机字母数字组合
//    *
//    * @param length 字符串长度
//    */
//  private def getRandomCharAndNumr(length: Integer) = {
//    val str = new StringBuilder
//    val random = new Random
//    var i = 0
//    while ( {
//      i < length
//    }) {
//      val b = random.nextBoolean
//      if (b) { // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
//        str.append((65 + random.nextInt(26)).asInstanceOf[Char])
//      }
//      else { // 数字
//        str.append(String.valueOf(random.nextInt(10)))
//      }
//
//      {
//        i += 1; i - 1
//      }
//    }
//    str.toString
//  }
//
//  /**
//    * 收藏
//    */
//  private def generateFavorites = {
//    val favorites = new AppFavorites
//    favorites.setCourse_id(rand.nextInt(10))
//    favorites.setUserid(rand.nextInt(10))
//    favorites.setAdd_time((System.currentTimeMillis - rand.nextInt(99999999)) + "")
//    val jsonObject = JSON.toJSON(favorites).asInstanceOf[Nothing]
//    packEventJson("favorites", jsonObject)
//  }
//
//  /**
//    * 点赞
//    */
//  private def generatePraise = {
//    val praise = new AppPraise
//    praise.setId(rand.nextInt(10))
//    praise.setUserid(rand.nextInt(10))
//    praise.setTarget_id(rand.nextInt(10))
//    praise.setType(rand.nextInt(4) + 1)
//    praise.setAdd_time((System.currentTimeMillis - rand.nextInt(99999999)) + "")
//    val jsonObject = JSON.toJSON(praise).asInstanceOf[Nothing]
//    packEventJson("praise", jsonObject)
//  }
//
//  /**
//    * 评论
//    */
//  private def generateComment = {
//    val comment = new AppComment
//    comment.setComment_id(rand.nextInt(10))
//    comment.setUserid(rand.nextInt(10))
//    comment.setP_comment_id(rand.nextInt(5))
//    comment.setContent(getCONTENT)
//    comment.setAddtime((System.currentTimeMillis - rand.nextInt(99999999)) + "")
//    comment.setOther_id(rand.nextInt(10))
//    comment.setPraise_count(rand.nextInt(1000))
//    comment.setReply_count(rand.nextInt(200))
//    val jsonObject = JSON.toJSON(comment).asInstanceOf[Nothing]
//    packEventJson("comment", jsonObject)
//  }
//
//  /**
//    * 生成单个汉字
//    */
//  private def getRandomChar = {
//    var str = ""
//    var hightPos = 0 //
//    var lowPos = 0
//    val random = new Random
//    //随机生成汉子的两个字节
//    hightPos = 176 + Math.abs(random.nextInt(39))
//    lowPos = 161 + Math.abs(random.nextInt(93))
//    val b = new Array[Byte](2)
//    b(0) = Integer.valueOf(hightPos).byteValue
//    b(1) = Integer.valueOf(lowPos).byteValue
//    try
//      str = new String(b, "GBK")
//    catch {
//      case e: UnsupportedEncodingException =>
//        e.printStackTrace()
//        System.out.println("错误")
//    }
//    str.charAt(0)
//  }
//
//  /**
//    * 拼接成多个汉字
//    */
//  private def getCONTENT = {
//    val str = new StringBuilder
//    var i = 0
//    while ( {
//      i < rand.nextInt(100)
//    }) {
//      str.append(getRandomChar)
//
//      {
//        i += 1; i - 1
//      }
//    }
//    str.toString
//  }
//}
//
