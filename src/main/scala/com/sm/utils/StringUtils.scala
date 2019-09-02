package com.sm.utils

object StringUtils {
  /**
    * 判断字符串是否为空
    * @param str 字符串
    * @return 是否为空
    */
  def isEmpty(str: String): Boolean = str == null || "" == str

  /**
    * 判断字符串是否不为空
    * @param str 字符串
    * @return 是否不为空
    */
  def isNotEmpty(str: String): Boolean = str != null && !("" == str)

  /**
    * 截断字符串两侧的逗号
    * @param str 字符串
    * @return 字符串
    */
  def trimComma(str: String): String = {
    var start = 0
    var end = str.length - 1
    while ( {
      (start <= end) && str.charAt(start) == ','
    }) start += 1
    while ( {
      (start <= end) && str.charAt(end) == ','
    }) end -= 1
    str.substring(start, end + 1)
  }

  /**
    * 补全两位数字
    * @param str
    * @return
    */
  def fulfuill(str: String): String = if (str.length == 2) str
  else "0" + str

  /**
    * 从拼接的字符串中提取字段
    * @param str       字符串
    * @param delimiter 分隔符
    * @param field     字段
    * @return 字段值
    */
  def getFieldFromConcatString(str: String, delimiter: String, field: String): String = {
    try {
      val fields = str.split(delimiter)
      for (concatField <- fields) { // searchKeywords=|clickCategoryIds=1,2,3
        if (concatField.split("=").length == 2) {
          val fieldName = concatField.split("=")(0)
          val fieldValue = concatField.split("=")(1)
          if (fieldName == field) return fieldValue
        }
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    null
  }

  def main(args: Array[String]): Unit = {
    val str = ",,aaa,,"
    System.out.println(trimComma(str))
  }
}
