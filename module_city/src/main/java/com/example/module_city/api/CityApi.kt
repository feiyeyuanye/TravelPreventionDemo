package com.example.module_city.api

import com.example.appbase.network.BaseApi
import com.example.appbase.network.BaseReqData
import com.example.module_city.bean.CityDataBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * FileName: CityApi
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 08:07
 * Profile: 城市清单 Api
 */
interface CityApi {

    /**
     * 加载城市数据
     * @param key 接口key值 无须传
     */
    @GET("citys")
    suspend fun loadCityData(@Query("key") key: String = BaseApi.KEY)
            : BaseReqData<List<CityDataBean>>
}
