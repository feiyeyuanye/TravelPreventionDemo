package com.example.module_risk_level.bean

import com.example.appbase.expand.splitData
import com.example.module_risk_level.bean.enum.DataTypeEnum

/**
 * FileName: RiskLevelDataBean
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 11:19
 * Profile:
 */
data class RiskLevelDataBean(
    val high_count: String,
    val high_list: List<RiskLevelDetailBean>,
    val low_count: String,
    val low_list: List<RiskLevelDetailBean>,
    val middle_count: String,
    val middle_list: List<RiskLevelDetailBean>,
    val updated_date: String,
    val list: List<RiskLevelDetailBean>
)

class RiskLevelDetailBean{
    val area_name: String?=null
    val city: String?=null
    // 地区信息的聚合
    val communitys: List<String>?=null
    val county: String?=null
    val county_code: String?=null
    val province: String?=null
    val type: String?=null
    // 自定义属性用于返回 communitys 集合中的所有值
    // splitData 方法是自定义的一个用来取 list 集合中所有数据的扩展函数
    // 函数将集合["A","B","C"] 处理为字符串"A\nB\nC\n"，便于通过 DataBinding 直接将结果绑定到 TextView 上。
    val communitysString: String
        get() {
            return communitys.splitData()
        }
    // 自定义的数据类型
    // 数据类型默认是0 为1 即为标题 高风险标题 2是 中风险标题
    var dataType = DataTypeEnum.DATA_IS_RISKLEVEL.ordinal
}

//data class High(
//    val area_name: String,
//    val city: String,
//    val communitys: List<String>,
//    val county: String,
//    val county_code: String,
//    val province: String,
//    val type: String
//)
//
//data class Low(
//    val area_name: String,
//    val city: String,
//    val communitys: List<String>,
//    val county: String,
//    val county_code: String,
//    val province: String,
//    val type: String
//)
//
//data class Middle(
//    val area_name: String,
//    val city: String,
//    val communitys: List<String>,
//    val county: String,
//    val county_code: String,
//    val province: String,
//    val type: String
//)