package com.sm.analysis

import scala.collection.mutable.ArrayBuffer

object TestArray {
  def main(args: Array[String]) {

    //     // 初始化一个长度为 5 的定长数组，其所有元素值均为 0
    //    val arr = new Array[Int](5)
    //
    //    // 打印定长数组，内容为数组的 hashcode 值
    //    println(arr)
    //
    //    // 将数组转换成数组缓冲，就可以看到原数组中的内容了
    //    // toBuffer 会将数组转换长数组缓冲
    //    println(arr.toBuffer)

    // 注意：如果 new，相当于调用了数组的 apply 方法，直接为数组赋值
    // 初始化一个长度为 10 的定长数组
    //    val arr2 = Array[Int](10)
    //    println(arr2.toBuffer)

    // 定义一个长度为 3 的定长数组
    //    val arr3 = Array("hello", "hi", "spark")
    //    println(arr3.toBuffer)

    //使用() 访问元素
    //    println(arr3(2))

    // 变长数组（数组缓冲）
    // 如果想使用数组缓冲，需要导入 import scala.collection.mutable.ArrayBuffer 包
    val arr4 = ArrayBuffer[Int]()
    //    println(arr4)
    //
    //    // 向数组缓冲的尾部追加一个元素
    //    // +=尾部追加元素,arr4 += 1, +=(,) 追加多个元素
    arr4 += (2, 3, 4, 5)
    //    println(arr4)
    //
    //    // 追加一个数组++=
    arr4 ++= Array(6, 7)
    //    println(arr4)

    // 追加一个List
    arr4 ++= List(8, 9)
    println(arr4)

    //    // 追加一个数组缓冲
    arr4 ++= ArrayBuffer(10, 11)
    println(arr4)

    //    // 在数组某个位置插入元素用 insert
    arr4.insert(0, -1, 0)
    println(arr4)
    //    //删除数组某个位置的元素用 remove
    arr4.remove(8, 2)
    println(arr4)

    // 截断尾部2个元素
    arr4.trimEnd(2)
    println(arr4)

    // 截断前3个元素
    arr4.trimStart(3)
    println(arr4)

    // 转成定长数组
    var arr5 = arr4.toArray
    println(arr5)
  }
}
