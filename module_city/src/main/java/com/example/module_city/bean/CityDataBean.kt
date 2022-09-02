package com.example.module_city.bean

import android.util.Log
import com.example.appbase.utils.Cn2SpellUtil
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * FileName: CityDataBean
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 08:36
 * Profile:
 */
//class CityDataBean {
//    private val provinceId: String? = null
//    private val province: String? = null
//    val citys: List<City>? = null
//
//    class City : Comparable<City> {
//        var cityNamePinyin: String? = null
//            get() {
//                field = city?.let {
//                    return Cn2SpellUtil.getPinYin(it)
//                }
//                return ""
//            }
//        var firstLetter: String? = null
//            get() {
//                val letter = Regex("[a-zA-Z]")
//                city?.let {
//                    field = Cn2SpellUtil.getPinYinFirstLetter(it)?.uppercase(Locale.getDefault())
//                    if (!field?.matches(letter)!!) {
//                        field = "#"
//                    }
//                    return field
//                }
//                return "#"
//            }
//
//        val city_id: String? = null
//        val city: String? = null
//        override fun compareTo(other: City): Int {
//            return if (firstLetter.equals("#") && !other.firstLetter.equals("#")) {
//                1
//            } else if (!firstLetter.equals("#") && other.firstLetter.equals("#")) {
//                -1
//            } else {
//                if (cityNamePinyin != null && other.cityNamePinyin != null) {
//                    cityNamePinyin!!.compareTo(other.cityNamePinyin!!, false)
//                } else {
//                    1
//                }
//
//            }
//        }
//    }
//}
//-------------------------------------------------------------------------------------------------------
data class CityDataBean(
    val citys: List<City>,
    val province: String,
    val province_id: String
)

data class City(
    val city: String,
    val city_id: String,
):Comparable<City> {
    /**
     * 因为需要实现字母列表索引功能，因此添加城市名称拼音及城市拼音首字母字段，并实现 Comparable 接口便于数据排序
     */
    var cityNamePinyin: String? = null
        // 重写 get 方法
        get() {
            city.let {
                field = Cn2SpellUtil.getPinYin(it)
                return field
            }
        }
    var firstLetter: String? = null
        get() {
            // val letter = Regex("[a-zA-Z]]") fuck,一直正则匹配失败。
            // 正则表达式类Regex
            val letter = Regex("[a-zA-Z]")
//            Log.d("TAG","letter----->$letter") 日志打印：[a-zA-Z]
            city.let {
                // Cn2SpellUtil 方法是依赖 pinyin4j-2.5.0.jar 开源库实现的汉语转拼音的工具类
                field = Cn2SpellUtil.getPinYinFirstLetter(it)?.uppercase(Locale.getDefault())
                //  Log.d("TAG","field------>$field") 日志打印：大写字母
                // 大写字母匹配正则表达式
                if (!field?.matches(letter)!!) {
//                    Log.d("TAG","未匹配到")
                    field = "#"
                }
                return field
            }
        }

    override fun compareTo(other: City): Int {
        return if (firstLetter.equals("#") && !other.firstLetter.equals("#")) {
            1
        } else if (firstLetter.equals("#") && other.firstLetter.equals("#")) {
            -1
        } else {
            if (cityNamePinyin != null && other.cityNamePinyin != null) {
                cityNamePinyin!!.compareTo(other.cityNamePinyin!!, false)
            } else {
                1
            }
        }
    }
}

