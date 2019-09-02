package com.sm.analysis
import scala.util.Random
object TestRand {
  private val rand = new Random
  def main(args: Array[String]): Unit = {

    for (i <- 0 to 10){
      println(rand.nextInt(2))  // 0 , 1
    }
  }
}
