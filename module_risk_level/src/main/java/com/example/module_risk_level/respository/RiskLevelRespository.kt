package com.example.module_risk_level.respository

import com.example.appbase.network.BaseReqData
import com.example.appbase.network.RetrofitServiceBuilder
import com.example.module_risk_level.api.RiskLevelApi
import com.example.module_risk_level.bean.RiskLevelDataBean

/**
 * FileName: RiskLevelRespository
 * Founder: Jiang Houren
 * Create Date: 2022/8/25 08:40
 * Profile:
 */
class RiskLevelRespository {
    // 创建 service 实例
    private var netWork = RetrofitServiceBuilder.createService(RiskLevelApi::class.java)

    suspend fun loadRiskLeverMessage():BaseReqData<RiskLevelDataBean>?{
        netWork?.let {
            return it.loadRiskLevelMessage()
        }
        return null
    }
}