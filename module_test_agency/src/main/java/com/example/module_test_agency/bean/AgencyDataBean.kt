package com.example.module_test_agency.bean

/**
 * FileName: AgencyDataBean
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 10:01
 * Profile: 检测机构数据返回
 */
data class AgencyDataBean(
    val city: String,
    val city_id: String,
    val `data`: List<Data>,
    val province: String
)

data class Data(
    val address: String,
    val city: String,
    val city_id: String,
    val name: String,
    val phone: String,
    val province: String
)