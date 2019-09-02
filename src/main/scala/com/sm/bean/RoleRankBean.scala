package com.sm.bean

import com.alibaba.fastjson.annotation.JSONField

import scala.beans.BeanProperty

case class RoleRankBean() extends Serializable {
  @BeanProperty var game_server:Int = _       //区服id
  @BeanProperty var role_id:String = _	    	// 角色id
  @BeanProperty var role_name:String = _	  	// 角色名
  @BeanProperty var role:String = _		  		  // 角色职业
  @BeanProperty var school:String = _	  			// 角色门派
  @BeanProperty var combat:Int = _    				// 角色战斗力
  @BeanProperty var role_vip:Int = _		  		// 角色vip等级
  @BeanProperty var before_rank:Int = _				// 升级前等级
  @BeanProperty var role_rank:Int	 = _  			// 升级后等级（即当前等级）

  override def toString = "game_server:"+ game_server + "," + "role_id:" + role_id + "," + "role_name:" + role_name + "role:" + role +  "," + "school:" + school +  "," + "combat:" + combat +  "," + "role_vip:" + role_vip +  "," + "before_rank:" + before_rank +  "," + "role_rank:" + role_rank

}
