package com.sm.analysis

import scala.collection.immutable._

object TestList {
  def main(args: Array[String]): Unit = {
    //创建不可变的集合
    //    val lst = List(1,2,3)
    //
    //    //将 0 插入到 lst 的前面生成一个新的 List
    //    val lst2 = 0 :: lst
    //    val lst3 = lst.::(0)
    //    val lst4 = 0 +: lst
    //    val lst5 = lst.+:(0)
    //
    //    //将一个元素添加到 lst1 的后面产生一个新的集合
    //    val lst6 = lst :+ 3
    //
    ////    println(lst2)
    //
    //    val lst0 = List(1,2,3)
    //    val lst1 = List(4,5,6)
    //
    //    //将 2 个 list 合并成一个新的 List
    //    val lst7 = lst0 ++ lst1
    //
    //    //将 lst0 插入到 lst1 前面生成一个新的集合
    //    val lst8 = lst0 ++: lst1
    //
    //    //将 lst0 插入到 lst1 前面生成一个新的集合
    //    val lst9 = lst1.:::(lst0)
    //
    ////    println(lst8)
    //
    //    val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
    //


    val lst = 1 :: 2 :: 3 :: 4 :: Nil
    println(lst)

    //    println(lst.isEmpty)

//    println(lst.head)

//    println(lst.init)

//    println(lst drop 2)

//    println(lst.splitAt(2))

//    var lst0=List(1,2,3,4)
//    println(lst0)
//    val lst1=List('1','2','3','4')
//    println(lst1)
//    println(lst0.zip(lst1))

    var lst0 = lst.toString
    println(lst0)
  }

  def isort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = {
    if (xs.isEmpty || x <= xs.head)
      x :: xs
    else
      xs.head :: insert(x, xs.tail)
  }
}
