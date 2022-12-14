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
     * ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? Comparable ????????????????????????
     */
    var cityNamePinyin: String? = null
        // ?????? get ??????
        get() {
            city.let {
                field = Cn2SpellUtil.getPinYin(it)
                return field
            }
        }
    var firstLetter: String? = null
        get() {
            // val letter = Regex("[a-zA-Z]]") fuck,???????????????????????????
            // ??????????????????Regex
            val letter = Regex("[a-zA-Z]")
//            Log.d("TAG","letter----->$letter") ???????????????[a-zA-Z]
            city.let {
                // Cn2SpellUtil ??????????????? pinyin4j-2.5.0.jar ?????????????????????????????????????????????
                field = Cn2SpellUtil.getPinYinFirstLetter(it)?.uppercase(Locale.getDefault())
                //  Log.d("TAG","field------>$field") ???????????????????????????
                // ?????????????????????????????????
                if (!field?.matches(letter)!!) {
//                    Log.d("TAG","????????????")
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

