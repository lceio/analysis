package com.sm.analysis

object TestArrayArg {
  def main(args: Array[String]): Unit = {
    // 定义一个数组
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8)

//    // 求和
//    println(arr.sum)
//
//    // 最大值
//    println(arr.max)
//
//    // 最小值
//    println(arr.min)
//
//    //
//    println(arr.toString())
//
//    // 指定分隔符
//    println(arr.mkString(","))
//    println(arr.mkString("<",",",">"))
//
//
//    var arr2 = Array(Array(1,2,3),Array(2,3,4))
//
//    println(arr2(0)(1))
//
//    for(i <- arr2) println( i.mkString(","))

    //增强 for 循环
//    for(i <- arr)
//      println(i)

    // 使用 to 可以生成一个序列作为脚标
//    for(i <- (0 to arr.length - 1).reverse)
//      println(arr(i))
//
//    //好用的 until 会生成一个 Range,reverse 是将前面生成的 Range 反转
//    for(i <- (0 until arr.length).reverse)
//      println(arr(i))

    //步长为 2
    for(i <- (0 until (arr.length, 2)))
      println(arr(i))

  }
}
