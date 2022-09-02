package com.example.module_test_agency.api

import com.example.appbase.network.BaseApi
import com.example.appbase.network.BaseReqData
import com.example.module_test_agency.bean.AgencyDataBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * FileName: AgencyApi
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 10:06
 * Profile: 检测机构 api
 */
interface AgencyApi {

    /**
     * 加载检测机构信息
     * @param key 接口key值 无须传
     * @param city_id 城市id，从城市选择页面返回
     */
    @GET("hsjg")
    suspend fun loadTestAgencyMessage(
        @Query("city_id") city_id: String,
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<AgencyDataBean>
}
