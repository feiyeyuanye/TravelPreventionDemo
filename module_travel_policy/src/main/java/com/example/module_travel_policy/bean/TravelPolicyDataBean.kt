package com.example.module_travel_policy.bean

import com.example.module_travel_policy.bean.enum.RiskLevelEnum

/**
 * FileName: TravelPolicyReqData
 * Founder: Jiang Houren
 * Create Date: 2022/8/25 11:43
 * Profile: 查询出行政策返回信息
 */
data class TravelPolicyDataBean(
    val f_tag: String,
    val fc_tag: String,
    val from_covid_info: Any,
    val from_info: PolicyDetailBean,  // 出发地
//    val from_info: FromInfo,
    val t_tag: String,
    val tc_tag: String,
    val to_covid_info: Any,
    val to_info: PolicyDetailBean    // 目的地
//    val to_info: ToInfo
)

class PolicyDetailBean{
    val city_id: String?=null
    val city_name: String?=null
    val health_code_desc: String?=null
    val health_code_gid: String?=null
    val health_code_name: String?=null
    val health_code_picture: String?=null
    val health_code_style: String?=null
    val high_in_desc: String?=null
    val low_in_desc: String?=null
    val out_desc: String?=null
    val province_id: String?=null
    val province_name: String?=null
    // 接口返回的风险等级，其字段值枚举类 RiskLevelEnum。
    val risk_level: String?=null
    // 是否是低风险地区
    val isLowRisk: Boolean
        get() {
            risk_level?.let {
                if (it == "0" || it == "1") {
                    return true
                }
            }
            return false
        }
    // 风险等级相关描述字段
    val riskLevelDesc: String
        get() {
            risk_level?.let {
                return RiskLevelEnum.getRiskLevelDesc(it)
            }
            return "未知"
        }
}


///**
// * 出发地政策信息
// */
//data class FromInfo(
//    val city_id: String,
//    val city_name: String,
//    val health_code_desc: String,
//    val health_code_gid: String,
//    val health_code_name: String,
//    val health_code_picture: String,
//    val health_code_style: String,
//    val high_in_desc: String,
//    val low_in_desc: String,
//    val out_desc: String,
//    val province_id: String,
//    val province_name: String,
//    val risk_level: String
//)
//
///**
// * 目的地政策信息
// */
//data class ToInfo(
//    val city_id: String,
//    val city_name: String,
//    val health_code_desc: String,
//    val health_code_gid: String,
//    val health_code_name: String,
//    val health_code_picture: String,
//    val health_code_style: String,
//    val high_in_desc: String,
//    val low_in_desc: String,
//    val out_desc: String,
//    val province_id: String,
//    val province_name: String,
//    val risk_level: String
//)