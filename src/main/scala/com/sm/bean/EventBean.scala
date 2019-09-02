package com.sm.bean

import scala.beans.BeanProperty

//class EventBean extends Serializable {
//  @BeanProperty var event_time : String = _
//  @BeanProperty var event_name : String = _
//}


case class EventBean() extends Serializable {
  @BeanProperty var event_time : String = _
  @BeanProperty var event_name : String = _

  override def toString: String = "event_time:" + event_time + "," + "event_name:" + event_name
}