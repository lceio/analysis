package com.sm.constants

// 常量类
object Constants {
  //spark作业的调度方式
  val SPARK_JOB_DEPLOY_MODE = "spark.job.deploy.mode"
  val SPARK_LOCAL_MODE = "local[4]"
  val SPARK_YARN_CLIENT_MODE = "yarn-client"
  val SPARK_YARN_CLUSTER_MODE = "yarn-cluster"
  val SPARK_NAME =s"${this.getClass.getSimpleName}"

  val HIVE_ODS = "hive.ods"

  //本地运行的session对应的task id
  val SPARK_JOB_SESSION_TASK_ID = "spark.job.session.task.id"
  //本地运行的page对应的task id
  val SPARK_JOB_PAGE_TASK_ID = "spark.job.page.task.id"
  //本地运行的product对应的task id
  val SPARK_JOB_PRODUCT_TASK_ID = "spark.job.product.task.id"
  //kafka的topic名称
  val SPARK_JOB_AD_KAFKA_TOPICS = "spark.job.ad.kafka.topics"

  val SPARK_JOB_AD_KAFKA_BOOTSTRAP_SERVERS = "spark.job.ad.kafka.bootstrap.servers"
  //jdbc driver
  val JDBC_DRIVER_CLASS_NAME = "driverClassName"
  //jdbc-url
  val JDBC_URL = "url"
  //jdbc-user
  val JDBC_USERNAME = "username"
  //jdbc-password
  val JDBC_PASSWORD = "password"


  // 任务参数
  val PARAM_START_DATE = "startDate"
  val PARAM_END_DATE = "endDate"
  val PARAM_START_AGE = "startAge"
  val PARAM_END_AGE = "endAge"
  val PARAM_CITIES = "cities"
  val PARAM_PROFESSIONALS = "professionals"
  val PARAM_SEX = "sex"
  val PARAM_TARGET_FLOW = "targetFlow" //页面切片


  // 聚合字段
  val FIELD_SESSION_ID = "sessionId"
  val FIELD_VISIT_LEN = "visitLen"
  val FIELD_STEP_LEN = "stepLen"
  val FIELD_SEARCH_KEYWORDS = "searchKeywords"
  val FIELD_CLICK_CATEGORY_IDS = "clickCategoryIds"
  val FIELD_ORDER_CATEGORY_IDS = "orderCategoryIds"
  val FIELD_PAY_CATEGORY_IDS = "payCategoryIds"
  val FIELD_START_TIME = "startTime"
  val FIELD_END_TIME = "endTime"
  val FIELD_AGE = "age"
  val FIELD_PROFESSIONAL = "professional"
  val FIELD_CITY = "city"
  val FIELD_SEX = "sex"
  val FIELD_SESSION_COUNT = "sessionCount"

  // 聚合统计字段-访问时长
  val TIME_PERIOD_1s_3s = "1s_3s"
  val TIME_PERIOD_4s_6s = "4s_6s"
  val TIME_PERIOD_7s_9s = "7s_9s"
  val TIME_PERIOD_10s_30s = "10s_30s"
  val TIME_PERIOD_30s_60s = "30s_60s"
  val TIME_PERIOD_1m_3m = "1m_3m"
  val TIME_PERIOD_3m_10m = "3m_10m"
  val TIME_PERIOD_10m_30m = "10m_30m"
  val TIME_PERIOD_30m = "30m"

  // 聚合统计字段-访问步长
  val STEP_PERIOD_1_3 = "1_3"
  val STEP_PERIOD_4_6 = "4_6"
  val STEP_PERIOD_7_9 = "7_9"
  val STEP_PERIOD_10_30 = "10_30"
  val STEP_PERIOD_30_60 = "30_60"
  val STEP_PERIOD_60 = "60"

}
