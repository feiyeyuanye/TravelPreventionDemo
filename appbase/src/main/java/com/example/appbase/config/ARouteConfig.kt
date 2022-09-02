package com.example.appbase.config

/**
 * FileName: ARouteConfig
 * Founder: Jiang Houren
 * Create Date: 2022/8/23 11:31
 * Profile: 路由管理配置文件
 * 定义了每个功能模块对应的路由地址
 */
object ARouteConfig {
    /**
     * 城市数据路由地址
     */
    const val CITY_DATA = "/city/citydata"

    /**
     * 检测机构信息
     */
    const val AGENCY_MESSAGE = "/agency/message"

    /**
     * 风险等级查询
     */
    const val RISK_LEVEL = "/risk/level"

    /**
     * 出行政策查询
     */
    const val TRAVEL_POLICY = "/travel/policy"
}