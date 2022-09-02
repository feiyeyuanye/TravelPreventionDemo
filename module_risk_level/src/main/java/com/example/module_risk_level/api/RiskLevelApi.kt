package com.example.module_risk_level.api

import com.example.appbase.network.BaseApi
import com.example.appbase.network.BaseReqData
import com.example.module_risk_level.bean.RiskLevelDataBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * FileName: RiskLevelApi
 * Founder: Jiang Houren
 * Create Date: 2022/8/25 08:37
 * Profile: 风险等级api
 */
interface RiskLevelApi {
    /**
     * 查询风险等级数据
     * @param key 接口key值 无须传
     */
    @GET("risk")
    suspend fun loadRiskLevelMessage(
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<RiskLevelDataBean>
}
