package com.sm.bean

import scala.beans.BeanProperty

case class MoneyFlowBean() extends Serializable {
  @BeanProperty var game_server:Int = _       //区服id
  @BeanProperty var role_id:String = _	    	// 角色id
  @BeanProperty var role_name:String = _			// 角色名
  @BeanProperty var role:String = _					  // 角色职业
  @BeanProperty var school:String = _	  			// 角色门派
  @BeanProperty var combat:Int = _    				// 角色战斗力
  @BeanProperty var role_vip:Int = _		  		// 角色vip等级
  @BeanProperty var role_rank:Int = _	  			// 角色等级
  @BeanProperty var item_id:String = _			  // 物品名称
  @BeanProperty var count:Int = _   	  			// 物品变化数量
  @BeanProperty var after_count:Int = _    		// 动作后的物品存量
  @BeanProperty var money_type:String = _		  // 变动时金钱的类型
  @BeanProperty var money:Int = _	  		    	// 变动时获得或消耗的金钱
  @BeanProperty var after_money:Int = _    		// 动作后的金钱数
  @BeanProperty var add_or_rduce:Int = _		  // 变动类型：0为增加 1为减少
  @BeanProperty var reason:String = _	  		  // 金钱流动原因
  @BeanProperty var sub_reason:String = _	  	// 金钱流动细节原因

  override def toString = "game_server:"+ game_server + "," + "role_id:" + role_id + "," + "role_name:" + role_name +  "," + "role:" + role +  "," + "school:" + school +  "," + "combat:" + combat +  "," + "role_vip:" + role_vip +  "," + "role_rank:" + role_rank +  "," + "item_id:" + item_id +  "," + "count:" + count +  "," + "after_count:" + after_count +  "," + "money_type:" + money_type +  "," + "money:" + money +  "," + "after_money:" + after_money +  "," + "add_or_rduce:" + add_or_rduce +  "," + "reason:" + reason +  "," + "sub_reason:" + sub_reason
}

